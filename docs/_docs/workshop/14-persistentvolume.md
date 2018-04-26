---
title: 14 - PersistentVolume
permalink: /docs/14-persistentvolume/
---

A PersistentVolume is a place where you can store stuff. It can be mounted into a pod, so your application use it as storage. I doesn't disappear when the pod is deleted. In other words, it's persistent.

Supporting a PersistentVolume that works regardless which node a pod is running takes some effort, so for this guide we will stick to a type of PersistentVolume that only exists on one node (called 'local-storage'). That means that if the pod runs on node A, stops, and is started on node B, it won't see the data, as it's now running on node B while the data is at A. For this guide, this is sufficient to learn about PersistentVolumes.

So, let's create PersistentVolume of type local-storage. Create a file `persistent-volume.yaml` with the following contents:

```yaml
apiVersion: v1
kind: PersistentVolume
metadata:
  name: example-pv
spec:
  capacity:
    storage: 10Mi
  # volumeMode field requires BlockVolume Alpha feature gate to be enabled.
  volumeMode: Filesystem
  accessModes:
  - ReadWriteOnce
  persistentVolumeReclaimPolicy: Delete
  storageClassName: local-storage
  local:
    path: /tmp/sample-app-data
  nodeAffinity:
    required:
      nodeSelectorTerms:
      - matchExpressions:
        - key: kubernetes.io/hostname
          operator: In
          values:
          - example-node
```

To use this volume in your pod, we have to adjust our my_deployment.yaml to this:


```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: sample-app-deployment
  labels:
    app: sample-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: sample-app
  template:
    metadata:
      labels:
        app: sample-app
    spec:
      containers:
      - name: sample-app
        image: ubuntu-k8s-1.local:30603/sample-app
        imagePullPolicy: Always
        ports:
        - containerPort: 8080
        livenessProbe:
          httpGet:
            path: /healthy
            port: 8080
        readinessProbe:
          httpGet:
            path: /healthy
            port: 8080
        volumeMounts:
        - name: greatestconfig
          mountPath: /config/greatestconfig.yml
          readOnly: true
          subPath: greatestconfig.yml
        - name: supersecret
          mountPath: "/secrets"
          readOnly: true
        - name: supremepersistence
          mountPath: /mydata
      volumes:
      - name: supersecret
        secret:
          secretName: mysecret
      - name: greatestconfig
        configMap:
          defaultMode: 0600
          name: myconfigmap
      - name: supremepersistence
        hostPath:
        path: /tmp/sample-app-data
```

Note the yaml for the `supremepersistence` at volumeMounts and volumes.

Update the deployment to utilize the new PersistentVolume:

`kubectl apply -f my_deployment.yaml`

Your pod should now restart with a directory ready at /mydata.

## Task: Write some data to the PersistentVolume

1. From the pod from deployment above, put some text in a file at /mydata.
1. Find out which node it is running on
1. Then restart the pod
1. Check if the node has changed. If it has, repeat the above steps, but put some other data in the file.
1. Restart the pod.
1. Verify that the data is still at the file you created under /mydata.

<details>
  <summary>Solution</summary>
<div markdown="1">

```
kubectl exec -it sample-app-deployment-6678db9979-kdlfr sh
$ echo hello there > /mydata/myfile.txt
$ exit

kubectl describe po sample-app-deployment-6678db9979-kdlfr
# Yields "Node:           ubuntu-k8s-2/..."

kubectl delete po sample-app-deployment-6678db9979-kdlfr

# Wait for a new pod to respawn
kubectl describe po sample-app-deployment-6755d84575-gdmw8

# Yields "Node:           ubuntu-k8s-2/..."
kubectl exec -it sample-app-deployment-6678db9979-kdlfr cat /mydata/myfile.txt
hello there

# Verification successful
```

</div>
</details>
