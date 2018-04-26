---
title: 14 - PersistentVolume
permalink: /docs/14-persistentvolume/
---

A PersistentVolume is a place where you can store stuff. It can be mounted into a pod, so your application use it as storage. I doesn't disappear when the pod is deleted. In other words, it's persistent.

The declaration of a PersistentVolume could look like this:

```yaml

apiVersion: v1
kind: PersistentVolume
metadata:
  name: pv0003
spec:
  capacity:
    storage: 5Gi
  volumeMode: Filesystem
  accessModes:
    - ReadWriteOnce
  storageClassName: slow
```

To see running PersistentVolumes in our cluster, run (output may be completely different):
```
kubectl get pv

NAME                                       CAPACITY   ACCESS MODES   RECLAIM POLICY   STATUS    CLAIM                               STORAGECLASS   REASON    AGE
pvc-24ca02b2-4270-11e8-8348-e4b318337de0   8Gi        RWO            Delete           Bound     kube-system/helm-repo-chartmuseum   hostpath                 8d
pvc-f67db106-3e5e-11e8-a5e2-e4b318337de0   200Gi      RWO            Delete           Bound     kube-system/docker-registry-pvc     hostpath                 14d

```

The perhaps most natural way to create a PersistentVolumes are by created PersistentVolumeClaims. Go to the
next section to see how.
