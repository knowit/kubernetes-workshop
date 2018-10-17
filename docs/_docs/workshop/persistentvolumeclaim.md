---
title: PersistentVolume
permalink: /docs/persistentvolumeclaim/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/storage/persistent-volumes/' %}
Persistent-Volume documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})


A PersistentVolume (PV) is a place where you can store stuff. It can be mounted into a pod, so your
application use it as storage. I doesn't disappear when the pod is deleted. In other words, it's persistent.

PersistentVolumes are created by PersistentVolumeClaims (PVC).

A PersistentVolumeClaim is a claim for a PersistentVolume from a storage provider.

With a PersistentVolumeClaim your app can request, for instance, 10GB of space. And it will automagically provision a volume that can be used without any concern of what the underlying technology is.

## Task: Create a PersistentVolumeClaim

Let's make a PersistentVolumeClaim, requesting 10Mb for our app. Create a file, `pvc.yaml` with the contents:

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: my-claim
spec:
  accessModes:
  - ReadWriteOnce
  resources:
    requests:
      storage: 10Mi
```

In your `deployment.yaml`, add a volume from the claim:

```yaml
...
    spec:
      ...
      volumes:
      - name: my-persistent-volume
        persistentVolumeClaim:
          claimName: my-claim
...
```

and then mount the volume at a path:



```yaml
...
    containers:
      ...
      volumeMounts:
        - name: my-persistent-volume
          mountPath: /mydata
...
```

Let's apply the claim:

`kubectl apply -f pvc.yaml`

Then verify that it has been "bound":

```
kubectl get pvc

NAME       STATUS    VOLUME                                     CAPACITY   ACCESS MODES   STORAGECLASS   AGE
my-claim   Bound     pvc-10a0ecc2-4962-11e8-978c-e4b318337de0   10Mi       RWO            hostpath       7m
```

Now we can apply our updated deployment.yaml:

`kubectl apply -f deployment.yaml`

Since the deployment has been updated since last time we applied it, Kubernetes should restart the pod in the
deployment.

Let's verify that a PersistentVolume has been created as the claim requested:

```
kubectl get pvc
NAME                                       CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS    CLAIM                               STORAGECLASS   REASON    AGE
pvc-10a0ecc2-4962-11e8-978c-e4b318337de0   10Mi       RWO            Delete           Bound     guybrush/my-claim                   hostpath                 15m
...
```

## Task: Verify that the PersistentVolumeClaim works

You can do this by
* writing to a file in the now mounted volume in the pod
* delete the deployment
* verify that the file still exists when a new pod is started

<details>
  <summary>Solution</summary>
  <div markdown="1">

Write to a file in the peristed volume:

```
kubectl get po
kubectl exec -it sample-app-deployment-7756ccb788-vflmz sh
/ # cd mydata
/mydata # ls
/mydata # echo hello there > hello
/mydata # exit
```

Delete the pod:

```
kubectl delete pod sample-app-deployment-7756ccb788-vflmz
```

Wait for the new pod to start (check with `kubectl get po`), then:

```
kubectl exec -it sample-app-deployment-a9w84f98n-fsdfm sh
/ # cd mydata
/mydata # ls
hello
/mydata # cat hello
hello there
/mydata # exit
```

Success! The file survived even though the pod was deleted. This is how our apps get persistence.

</div>
</details>
