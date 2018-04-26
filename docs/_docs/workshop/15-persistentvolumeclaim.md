---
title: 15 - PersistentVolumeClaim
permalink: /docs/15-persistentvolumeclaim/
---

A PersistentVolumeClaim is a claim for a PersistentVolume from a storage provider. A Kubernetes cluster can be set up with a lot of different storage providers (see "Storage classes" in documentation for details). Some examples are AzureDisk, NFS and GCEPersistentDisk, and they have varying properties, such as performance.

With a PersistentVolumeClaim, your app can request to just get for instance 10GB of space without caring about the details of the volume that the Kubernetes cluster can provide. For instance, along with your other YAML configuration for the app (deployment.yaml, service.yaml, etc), you could have a pvc.yaml like this:

```yaml
apiVersion: v1
kind: PersistentVolumeClaim
metadata:
  name: maven-repo-claim
spec:
  accessModes:
    - ReadWriteMany
  resources:
    requests:
      storage: 30Gi
```

And in the deployment.yaml, you request a PersistentVolume to be mounted from a PersistentVolumeClaim like this:

```yaml
...
   volumes:
   - name: jenkins-home
     persistentVolumeClaim:
       claimName: maven-repo-claim
...
```

So when doing
```
kubectl apply -f pvc.yaml
kubectl apply -f deployment.yaml
```

you could run:

```
kubectl get pv maven-repo-claim
```

Sample output:

```
NAME            STATUS    VOLUME           	  CAPACITY   ACCESSMODES   STORAGECLASS   AGE
task-pv-claim   Bound     maven-repo-volume   30Gi       RWO           manual         30s
```

And:

```
kubectl get pv maven-repo-volume
```

```
NAME             CAPACITY   ACCESSMODES   RECLAIMPOLICY   STATUS    CLAIM                   	STORAGECLASS   REASON    AGE
task-pv-volume   30Gi       RWO           Retain          Bound     default/maven-repo-volume   manual                   2m
```

