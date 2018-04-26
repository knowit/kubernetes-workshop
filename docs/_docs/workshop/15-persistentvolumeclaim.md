---
title: 15 - PersistentVolumeClaim
permalink: /docs/15-persistentvolumeclaim/
---

A PersistentVolumeClaim is a claim for a PersistentVolume from a storage provider. A Kubernetes cluster can be set up with a lot of different storage providers (see "Storage classes" in documentation for details). Some examples are AzureDisk, NFS and GCEPersistentDisk, and they have varying properties, such as performance.

With a PersistentVolumeClaim, your app can request to just get for instance 10GB of space without caring about the details of the volume that the Kubernetes cluster can provide.

## Task: Create a PersistentVolumeClaim, and verify that it works

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

In your deployment.yaml, request a PersistentVolume to be mounted from the PersistentVolumeClaim:

```yaml
...
    volumes:
    - name: supremepersistence
      persistentVolumeClaim:
        claimName: my-claim
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

