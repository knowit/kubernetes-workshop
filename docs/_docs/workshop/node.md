---
title: 03 - Node
permalink: /docs/node/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/architecture/nodes/' %}
Node documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})


A node is a machine. It runs the docker containers (within
[pods]({{ site.baseurl }}{% link _docs/workshop/pod.md %})
)

A Kubernetes cluster consists of one or more nodes. To give your cluster more juice (computing power), you simply add
more nodes.

To list nodes in the cluster:

`kubectl get nodes`

## Task

Find out the IP of one of the kubernetes nodes.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: kubectl get node

OK, let's find some nodes:

`kubectl get nodes`

Then lets find the IP of one of the nodes

`kubectl get node gke-cluster-1-default-pool-eb6174f5-0xkb -o yaml`

Among the output, I see:
```
  addresses:
  - address: 35.234.105.108
```

So, that's the IP.

### Solution 2: kubectl describe node

`kubectl describe node gke-cluster-1-default-pool-eb6174f5-0xkb`

Among the output, I see:
```
Addresses:
  InternalIP:	10.156.0.5
  ExternalIP:	35.234.105.108
```

So, that's the IP addresses.

`kubectl describe` can provide info about a node (or any resource, like `kubectl get pod my-pod`).
`kubectl describe` usually gathers information from more sources (like "events") than just the YAML
description.

 </div>
</details>
