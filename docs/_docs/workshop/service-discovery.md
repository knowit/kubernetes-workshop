---
title: Service discovery
permalink: /docs/service-discovery/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/services-networking/service/#discovering-services' %}
Service discovery documentation:
{% comment %}
* [Offline]({{host.offline}}{{path}})
{% endcomment %}
* [Online]({{host.online}}{{path}})

By service discovery, we mean your app's possibility to communicate with a Kubernetes Service.

In your app code, you can reach other services by their service name. Kubernetes' DNS makes this possible. So for instance you could reach a web server that is exposed through a service by running the command

```
curl http://someservice
```

in a container in a pod. To reach a service outside the current namespace, run

```
curl http://someservice.somenamespace
```

(FYI, curl is a tool to transfer data for instance using HTTP.)

Let's try this. We'll use your existing service to curl from, and fire up one new service in the same namespace and one in another workspace.

## Task: Create service in same namespace

Fire up a pod running the image `yngvark/nginx-curl` and a service that exposes the pod.

<details>
 <summary>Solution</summary>
 <div markdown="1">

```
kubectl create deployment my-nginx --image=yngvark/nginx-curl
kubectl expose deployment my-nginx --port 8085 --target-port 80
```

 </div>
</details>

## Do a request to a service

Now that we have two services, they can reach each other. From your the front end pod created earlier in this workshop, let's see if we can reach the new nginx (which is a web server) with `curl`. 

`kubectl exec -it ez-frontend-845bb77cbd-rnj6r bash`

We're now in a shell inside the container in the pod. Let's now try to reach the service we just exposed:

`curl -vvv http://my-nginx:8085`

This should produce the HTML of the Nginx welcome page:

```
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
    body {
        width: 35em;
        margin: 0 auto;
        font-family: Tahoma, Verdana, Arial, sans-serif;
    }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
/ # curl -vvv http://my-nginx:8085
* Rebuilt URL to: http://my-nginx:8085/
*   Trying 10.99.107.184...
* TCP_NODELAY set
* Connected to my-nginx (10.99.107.184) port 8085 (#0)
> GET / HTTP/1.1
> Host: my-nginx:8085
> User-Agent: curl/7.59.0
> Accept: */*
> 
< HTTP/1.1 200 OK
< Server: nginx/1.13.12
< Date: Thu, 26 Apr 2018 08:42:05 GMT
< Content-Type: text/html
< Content-Length: 612
< Last-Modified: Mon, 09 Apr 2018 16:01:09 GMT
< Connection: keep-alive
< ETag: "5acb8e45-264"
< Accept-Ranges: bytes
< 
<!DOCTYPE html>
<html>
<head>
<title>Welcome to nginx!</title>
<style>
    body {
        width: 35em;
        margin: 0 auto;
        font-family: Tahoma, Verdana, Arial, sans-serif;
    }
</style>
</head>
<body>
<h1>Welcome to nginx!</h1>
<p>If you see this page, the nginx web server is successfully installed and
working. Further configuration is required.</p>

<p>For online documentation and support please refer to
<a href="http://nginx.org/">nginx.org</a>.<br/>
Commercial support is available at
<a href="http://nginx.com/">nginx.com</a>.</p>

<p><em>Thank you for using nginx.</em></p>
</body>
</html>
* Connection #0 to host my-nginx left intact
```

In other words, you were able to do a HTTP request to `http://my-nginx:8085`, which were resolved by the
Kubernetes DNS.

## Task: Do a request to a service in another namespace

Just pick any of the other students' services, or use one that we should, but may have forgotten to, deploy in the namespace `default`. To find services in other namespaces, run:

`kubectl get svc --all-namespaces`

<details>
	<summary>Solution</summary>
	<div markdown="1">

```
# Pick any pod in your namespace to do the request from
kubectl exec -it my-nginx-7d4b689dcb-2j57k sh

# Now in the pod, run
$ curl http://my-nginx.default:8085
```

Then you should get a reply from the nginx welcome page.

</div>
</details>
