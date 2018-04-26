---
title: 02 - KubeCtl
permalink: /docs/02-kubectl/
---

# Architecture
![Kubernetes architecture]({{ "/assets/img/k8s-architecture.png" | relative_url }})

## Kubelet
Manages that containers are running in pods. This is the main component of
 kubernetes, and initiates the other master components.

## Master
### Apiserver
The Apiserver communicates with the cluster either by using the kubelet or
 directly within the cluster. All communication from the cluster must use the api-server.

### etcd
Manages state for the kubernetes cluster

### kube-scheduler
Watches for pods with no nodes, and assigns a node to the pod

### kube-controller-manager

Runs different controllers that controls the internal logic of the cluster.
For example alerting when a node goes down, controlling replication of pods, maintaining api tokens and secrets.


# Kubectl

kubectl is a handy tool for dealing with the Apiserver (api). We are going to
use this tool extensively during this workshop, so we should get to know it.

> To enable autocomplete
`source <(kubectl completion bash)`

> Use `kubectl -h` if you are stuck

Kubectl can help you along the way, if you ever get stuck try using `kubectl explain`
to find more info about what you are working with.
Important commands for debugging include:
- explain
- describe
- log
- exec


## Task 1

Aquire the kubeconfig that has been generated for you, and place it at `$HOME/.kube/config`. Use `kubectl` to view you have been given. Look for information about the cluster, username and context

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: View Config

- `kubectl config view`

 </div>
</details>  


## Task 2

Use `kubectl explain ` to get some info on a resource (e.g. `Pod`)
