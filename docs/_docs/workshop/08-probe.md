---
title: 08 - Readiness and liveness probe
permalink: /docs/08-probe/
---

# Readiness and liveness probe

The readiness and liveness probe are diagnostic tools used to verify the state of a container. The probes work by either

1) execute a command inside the container. A return value of 0 indicates the container is in a good state.

2) execute a HTTP request to an endpoint inside the containter. A status code of equal and above 200 and below 400 indicates the container is in a good state.

3) execute a TCP connection to a port on the containers IP. An open port indicates that the container is in a good state.

The readiness probe indicates that the container is ready to service requests. Before the container is in a ready state, the kubelet will not direct any requests from any service to the container.

The liveness probe indicates that the container is running healthy. When the liveness probe fails, the kubelet will restart the container (or just kill it, if the containers restartPolicy is configured in such a way).

Both readiness and liveness probes are optional. Without them, the kubelet will consider that the container is in a good state at all times.

## Task

Extend your deployment to include readiness and liveness probes. The sample application has endpoints at ***/healthy*** and ***/nonhealthy***, that return HTTP 200 and HTTP 418, respectively. Use these endpoints to either make your container seem to be in a good state or bad state.

<details>
  <summary>Solution</summary>
  <div markdown="1">

### Solution

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
```

  </div>
</details>
