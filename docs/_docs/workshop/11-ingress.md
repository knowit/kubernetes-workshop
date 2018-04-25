---
title: 11 - Ingress
permalink: /docs/11-ingress/
---

# Ingress

Until now we have only created deployments and services that are accessible from inside the cluster. In a real world scenario, you might also want to expose your services to the rest of the world (i.e. the internet).

An Ingress is a collection of rules that allow an inbound request to our cluster to reach services. The Ingress can be configured to give services externally-reachable URLs, load balance traffic, terminate SSL, offer name based virtual hosting, and more.

For any Ingress' to work, the cluster needs an Ingress controller. The Ingress controller is basically a reverse proxy, forwarding requests from the master node to the internal network of the kubelets that run the containers. The most common Ingress controller uses Nginx as the backend.

An Ingress can look like the following

```yaml
apiVersion: extensions/v1beta1
kind: Ingress
metadata:
  name: test-ingress
  annotations:
    nginx.ingress.kubernetes.io/rewrite-target: /
spec:
  rules:
  - http:
      paths:
      - path: /mypath
        backend:
          serviceName: myService
          servicePort: 80
```

In the Ingress above, we can see that the path ***/mypath*** allows access to the service ***myService*** on port 80.
