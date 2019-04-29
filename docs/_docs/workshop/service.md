---
title: Service
permalink: /docs/service/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/services-networking/service/' %}
Service documentation:
{% comment %}
* [Offline]({{host.offline}}{{path}})
{% endcomment %}
* [Online]({{host.online}}{{path}})

Pods may live where ever they want, and replicate or go down at any time during the deployment lifetime. New IPs are assigned, and wil lead to problems when trying to connect to other pods in the cluster.

To remedy this we have a resource in kubernetes called Service.
Services maintain a stable IP and defines a set of logical Pods and how to access them.

![text](../../img/k8s-service-pod-access.jpg)

Services defines which pods to connect by utilizing the labels on the pods.

> For instance: To create a service accessible on port 80, that connects to a pod named [pod-name]
on port 8080 (the pod's port), run: `kubectl expose pod [pod-name] --port=80 --target-port=8080`

Inspecting a service reveals that the service type is `ClusterIP`

There are 3 main types of services: `NodePort`, `LoadBalancer` and `ClusterIP`
- `ClusterIp` Pods accessed within the cluster only.
- `NodePort` Pods are exposed on all the hosts on a random port.
- `LoadBalancer` The cloud provider automatically provisions a LB for your service, creating an external IP for the service.


## Task 1

Expose your frontend deployment with a NodePort service on port 8080.
Expose your backend deployment with a ClusterIP service on port 8080.

Find out the NodePort port of your new frontend service.

Then, get the IP of any of the nodes in our cluster, and then open that IP with the frontend
service's NodePort port in a browser. This is where the frontend service can be accessed
publicly on the Internet.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: Exposing a pod with service
Frontend:
- `kubectl expose deployment ez-frontend --port 8080 --type NodePort`
- `kubectl get svc` # Note the second part of the Port `8080:34567`
- `kubectl get nodes -o wide` 
- Paste one of the node ips into a browser followed by the generated port number. `http://1.2.3.4:34567`

Backend:
- `kubectl expose deployment workshop-api-deployment --port 8080`
 </div>
</details>
{% comment %}
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
{% endcomment %}
