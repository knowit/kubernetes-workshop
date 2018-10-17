---
title: Kubectl
permalink: /docs/kubectl/
---

{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/reference/kubectl/cheatsheet/' %}
Kubernetes command-line tool cheatsheet:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})


The Kubernetes command-line tool, kubectl controls the Kubernetes cluster manager.

We are going to use this tool extensively during this workshop, so we should get to know it.

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

*For the offline workshop:* Aquire the kubeconfig that has been generated for you, and place it at `$HOME/.kube/config`.

Use `kubectl` to view the config you have been given. Look for information about the cluster, username and context

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: View Config

- `kubectl config view`

 </div>
</details>  


## Task 2

Use `kubectl explain ` to get some info on a resource (e.g. `pod`)

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: View Config

- `kubectl explain service`

 </div>
</details>  