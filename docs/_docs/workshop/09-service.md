---
title: 09 - Service
permalink: /docs/09-service/
---


Pods may live where ever they want, and replicate or go down at any time during the deployment lifetime. New IPs are assigned, and wil lead to problems when trying to connect to other pods in the cluster.

To remedy this we have a resource in kubernetes called Service.
Services maintain a stable IP and defines a set of logical Pods and how to access them.

![text](../../img/k8s-service-pod-access.jpg)

Services defines which pods to connect by utilizing the labels on the pods.

> E.g. By creating a service with kubectl we use the `expose` command
With the following command we create a service accessable by port 80,
that connects to a pod with the name [pod-name] and container-port 8080.
`kubectl expose [pod-name] --port=80 --targetPort=8080`

Inspecting a service reveals that the service type is `ClusterIp`

There are 3 main types of services: `ClusterIp`, `NodePort`, `LoadBalancer`
- `ClusterIp` Pods accessed within the cluster only
- `NodePort` Each node is assigned a port and the pods is accessed with the
ip from the node (where the pod lives) + the node port. E.g. `ubuntu-k8s-2.local:30042`
- `LoadBalancer` The cloud provider automatically provisions a LB for your service,
not applicable in this scenario.


## Task 1

Expose your deployment (pods) with a service.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: Just do it

- Change the version labels in the deployment file
- kubectl deploy ..
- kubectl expose [pod-name]

 </div>
</details>

## Task 2
Deploy a new version of your application, and expose it with a sercvice.
Edit the service so that the pods it defines are of both the old and the new version.
By doing multiple curl call to the service, the response should vary between v1 and v2.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 2: kubectl describe node

- kubectl edit svc pod-name
under the `selector tag`, remove all other tags other than `k8s-app: ...`
this will select all pods with the `k8s-app` label.

 </div>
</details>
