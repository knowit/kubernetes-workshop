---
title: 03 - Namespace
permalink: /docs/03-namespace/
--- 
 
Namespaces can be used in many different ways
 * to separate the cluster by environment
 * to provide a space/resources for your users
 * to provide scoping for names(resource names must be unique per namespace).

Most objects in kubernetes live in a namespace.
   
We have provided a namespace for each user today and you should already be all set to start deploying.
Managing namespaces is an admin task.

## View namepsace

```bash
$ kubectl get namespaces
```

## Use namespace

```bash
$ kubectl get pods -n ${ns-name} | --namespace=${ns-name}
```
