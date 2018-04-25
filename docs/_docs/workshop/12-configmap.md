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
  name: myconfigmap
  labels:
    app: sample-app
data:
  greatestconfig.yml: |-
    - module: myawesomemodule
      start_at_boot: true
```

### Solution, Mounting the ConfigMap to a file inside the container

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
        volumeMounts:
        - name: greatestconfig
          mountPath: /config/greatestconfig.yml
          readOnly: true
          subPath: greatestconfig.yml
      volumes:
      - name: greatestconfig
        configMap:
          defaultMode: 0600
          name: myconfigmap
```
  </div>
</details>
