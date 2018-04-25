---
title: 02 - KubeCtl
permalink: /docs/02-kubectl/
---

# TestEdit

# Architecture
## Kubelet
Manages that containers are running in pods. This is the main component of kubernetes, and initiates the other master components.

## Master
### Apiserver
The Apiserver communicates with the cluster either by using the kubelet or directly within the cluster.

### etcd
Manages state for the kubernetes cluster

### kube-scheduler
Watches for pods with no nodes, and assigns a node to the pod

### kube-controller-manager

Runs different controllers that controls the internal logic of the cluster. For example alerting when a node goes down, controlling replication of pods, maintaining api tokens and secrets.
