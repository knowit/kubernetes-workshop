---
title: 06 - Namespace
permalink: /docs/06-namespace/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/overview/working-with-objects/namespaces/' %}
Namespace documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})


Namespaces can be used in many different ways; to separate the cluster by environment,
to provide a space/resources for your users, to provide scoping for names(resource names must be unique per namespace).  
We have provided a namespace for each user today, and you will each work separate from each other in the beginning at least.


We have used RBAC (Role Based Access Control) to restrict each user into one namespace only.
RBAC is used by binding a `Role` (which defines some allowed actions) to a `User`.

You are allowed to watch other namespaces, but not interact as we have set a cluster-wide role for all authenticated users(view). 

There are 3 main roles that you are free to use:
- view (read access only)
- edit (access to edit and access most resources)
- admin (access to all resource within a ns)

These `Roles` are applied to users (or groups) by using a `RoleBinding`.

`Role` -- `RoleBinding` -- `User`

{% assign rbac = '/docs/admin/authorization/rbac/' %}
RBAC documentation:
* [Offline]({{host.offline}}{{rbac}})
* [Online]({{host.online}}{{rbac}})


## Task 1

List all the namespaces in the cluster

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: List namespaces

- `kubectl get ns ` (short hand for namespace)

 </div>
</details>  

## Task 2

Try to port-forward a to a pod in a different namespace

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: Try to port forward

- `kubectl port-forward [pod-name] --namespace [namespace]`

 </div>
</details>

## Task 3

Allow another user access to your namespace and get them to access your pods!


<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: Allowing Access

- `kubectl create rolebinding [new-rolebinding-name] --user=[username] --clusterrole=[edit|admin]`

 </div>
</details>
