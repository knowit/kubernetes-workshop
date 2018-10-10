---
title: 10 - Service
permalink: /docs/10-service/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/services-networking/service/' %}
Service documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})

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
- `NodePort` Each node is assigned a port and the pods is accessed with the ip from the node (where the pod lives) + the node port (ip:NodePort). E.g. `ubuntu-k8s-2.local:30042`
- `LoadBalancer` The cloud provider automatically provisions a LB for your service, not applicable in this scenario.


## Task 1

Expose your deployment (pods) with a NodePort service. 

Visit the url where the service runs.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: Exposing a pod with service

- `kubectl expose [pod-name] --port 80 --targetPort 8080 --type NodePort`
- `kubectl get svc` # Note the node port number
- `kubectl get po -owide ` # Check which node the pods are located
- Access the pod with the browser on: `node:NodePort`, e.g. `ubuntu-k8s-1.local:34567`
 </div>
</details>

## Task 2
Deploy a new version of your application, and expose it with a service.

Edit the service so that the pods it defines are of both the old and the new version.

By doing multiple curl call to the service, the response should vary between v1 and v2.

*You might need to scale the deployments up to 3 to get the desired result*

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 2: versioning

- Change the deployment name and version.
- `kubectl apply -f deployment.yaml` to deploy the new version
- `kubectl expose [pod-name] ... ` as the last task  
- `kubectl edit svc [svc-name]`
under the `selector tag`, remove all other tags other than `k8s-app: ...`
this will select all pods with the `k8s-app:my-app` label which should be both versions.

 </div>
</details>
