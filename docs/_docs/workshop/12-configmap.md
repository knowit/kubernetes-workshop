---
title: 12 - ConfigMap
permalink: /docs/12-configmap/
---

# ConfigMap

A ConfigMap allows the storage of configuration outside of the container image. This provides portability for containers. ConfigMaps can be used to store environment variables or to populate volumes inside the container.

## Task

Create a configuration file inside the container using a ConfigMap. Mount the file at ***/config/greatestconfig.yml***. The enpoint ***/configmap*** in the sample app can then be used to view contents of the ConfigMap.

<details>
  <summary>Solution</summary>
  <div markdown="1">

### Solution, ConfigMap

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: metricbeat-daemonset-modules
  namespace: monitoring
  labels:
    k8s-app: metricbeat
    kubernetes.io/cluster-service: "true"
data:
  greatestconfig.yml: |-
    - module: kubernetes
      metricsets:
        - event
      period: 10s
```

### Solution, Mounting the ConfigMap to a file inside the container

```yaml
  volumeMounts:
  - name: metricbeat-config
    mountPath: /etc/metricbeat.yml
    readOnly: true
    subPath: metricbeat.yml
volumes:
- name: metricbeat-config
  configMap:
    defaultMode: 0600
    name: metricbeat-config
```
  </div>
</details>
