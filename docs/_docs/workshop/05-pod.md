---
title: 05 - Pod
permalink: /docs/05-pod/
---

A Pod is a group of docker (or other) containers. Think of a pod as a microservice. A microservice can for
instance consist of two containers, one for app logic, and one container for MySQL (for instance).

To show all pods running in your Kubernetes cluster:

```
kubectl get po --all-namespaces
```

Containers within a pod share an IP address and port space, and can find each other via localhost.

So in your app, your Java code could connect to MySQL on "jdbc:mysql://localhost:3306/db".

# YAML

Pods and other resources in Kubernetes can be described in YAML. Here is the YAML for a Pod:

```yaml
apiVersion: v1
kind: Pod
metadata:
name: myapp-pod
labels:
  app: myapp
spec:
  containers:
  - name: myapp-container
    image: busybox
    command: ['sh', '-c', 'echo Hello Kubernetes! && sleep 3600']
```

After we have shown you [Deployments]({{ site.baseurl }}{% link _docs/workshop/07-deploy.md %}), we're ready
to deploy pods to our cluster!
