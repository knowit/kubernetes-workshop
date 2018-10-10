---
title: 03 - Node
permalink: /docs/03-node/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/architecture/nodes/' %}
Node documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})


A node is a machine. It runs the docker containers (within
[pods]({{ site.baseurl }}{% link _docs/workshop/05-pod.md %})
)

To list nodes in the cluster:

`kubectl get nodes`

Try to get more details about a node:

`kubectl describe node ubuntu-k8s-1`

A Kubernetes cluster consists of one or more nodes. To give your cluster more juice (computing power), add
nodes.

## Task

Find out the IP of the kubernetes master and one of the kubernetes nodes.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: kubectl get node

OK, let's find the master:

`kubectl get nodes`

The node with ROLES "master" seems to be the master. Let's find its IP.

`kubectl get node ubuntu-k8s-1 -o yaml`

Among the output, I see:
```
  addresses:
  - address: 192.168.1.29
```

So, that's the ip.

### Solution 2: kubectl describe node

`kubectl describe node ubuntu-k8s-1`

Here I see this line:

`flannel.alpha.coreos.com/public-ip=192.168.1.29`

It's a bit more cryptic than the output for `kubectl get node`, but we include this solution so you know
that `kubectl describe` can provide info about a node (or any resource, like `kubectl get pod my-pod`).
`kubectl describe` usually gathers information from more sources (like "events") than just the YAML
description.

 </div>
</details>
