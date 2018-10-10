---
title: ConfigMap
permalink: /docs/configmap/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/tasks/configure-pod-container/configure-pod-configmap/' %}
ConfigMap documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})

A ConfigMap allows the storage of configuration outside of the container image. This provides portability for containers. ConfigMaps can be used to store environment variables or to populate volumes inside the container.

A configmap can either be created manually by specifying a yaml file, or by `kubectl create configmap`


## Task 1

Create a configmap with `kubectl`. The configmap should contain a literal `BACKEND_URL`. The value should be a callable service in the cluster, and should be in the form: `http://[service-name].[namespace]:[service-port]`. Example: `BACKEND_URL="http://super-backend.tord-kloster:80"`
<details>
</details>

## Task 2

Load the configmap as environment variables in the pod.

<details>
  <summary>Step-by-step Task 2</summary>
  <div markdown="1">

- <details>
  <summary>Use kubectl create configmap </summary>
  <div markdown="1">
  `kubectl create configmap my-cool-configmap --from-literal VERSION=v0.1.0 `
  </div>
  </details>

- In the deployment yaml file configure the container to use environment from the configmap

```yaml
containers:
  - name: {....}
    envFrom:
    - configMapRef:
        name: # name of your configmap
```  
</div>
</details>

{{ % comment % }}
## Task 3

Create a configuration file inside the container using a ConfigMap. Mount the file at ***/config/greatestconfig.yml***. The enpoint ***/configmap*** in the sample app can then be used to view contents of the ConfigMap.

<details>
  <summary>Step-by-step Task 3</summary>
  <div markdown="1">

### Step 1, ConfigMap

```yaml
apiVersion: v1
kind: ConfigMap
metadata:
  name: ## give it a name
  labels:
    ## and a describing label or two
data:
  greatestconfig.yml: |-
    - module: myawesomemodule
      start_at_boot: true
```

### Step 2, In your previous deployment file, add the following

```yaml
apiVersion: apps/v1
kind: Deployment
...
      containers:
      - name: sample-app
        image: ubuntu-k8s-1.local:30603/sample-app
        imagePullPolicy: IfNotPresent
        ports:
        - containerPort: 8080
        volumeMounts:
        - name: # Name of the volume you want to mount
          mountPath: /config/greatestconfig.yml
          readOnly: true
          subPath: greatestconfig.yml
      volumes:
      - name: # Give the volume a name
        configMap:
          defaultMode: 0600
          name: # reference the configmap name
```
  </div>
</details>

{{ % endcomment % }}
