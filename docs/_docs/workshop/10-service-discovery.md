---
title: 10 - Service discovery
permalink: /docs/10-service-discovery/
---

By service discovery, we mean your app's possibility to communicate with the Kubernetes Services that we just
learned about.

In your app code, you can reach other services by their service name. Kubernetes DNS makes this possible. So 
for example, you could reach a web server that is exposed through a service by running the command
`curl http://someservice` in a container in a pod. To reach a service outside the current namespace, run
`curl http://someservice.somenamespace`.

Let's try this.

TODO run svc in your container

TODO run svc in other namespace

