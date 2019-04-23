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

> E.g. By creating a service with kubectl we use the `expose` command
With the following command we create a service accessable by port 80,
that connects to a pod with the name [pod-name] and container-port 8080.
`kubectl expose pod [pod-name] --port=80 --target-port=8080 --type=ClusterIP`

Inspecting a service reveals that the service type is `ClusterIP`

There are 3 main types of services: `NodePort`, `LoadBalancer`
- `ClusterIp` Pods accessed within the cluster only
- `NodePort` Pods are exposed with the host IP + port.
- `LoadBalancer` The cloud provider automatically provisions a LB for your service, creating an external IP.


## Task 1

Expose your frontend deployment with a LoadBalancer service.
Expose your backend deployment with a ClusterIP service.

Visit the IP where the frontend service runs.

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution 1: Exposing a pod with service

- `kubectl expose deployment ez-frontend --port 80 --target-port 8080 --type LoadBalancer`
- `kubectl get svc` # Note the EXTERNAL-IP
- Paste the ip into a browser and some response should appear once the loadbalancer is created.
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
