---
title: 10 - Service discovery
permalink: /docs/10-service-discovery/
---

Docs: http://ubuntu-k8s-1.local:30827/docs/concepts/services-networking/service/#discovering-services

By service discovery, we mean your app's possibility to communicate with a Kubernetes Service.

In your app code, you can reach other services by their service name. Kubernetes' DNS makes this possible. So for instance you could reach a web server that is exposed through a service by running the command
`curl http://someservice` in a container in a pod. To reach a service outside the current namespace, run
`curl http://someservice.somenamespace`.

(FYI, curl is a tool to transfer data for instance using HTTP.)

Let's try this. We'll use your existing service to curl from, and fire up one new service in the same namespace and one in another workspace.

## Task: Create service in same namespace

Fire up a pod running the `nginx` image and a service that exposes the pod.

<details>
 <summary>Solution</summary>
 <div markdown="1">

```
kubectl create deployment my-nginx --image=ubuntu-k8s-1.local:30603/nginx-curls
kubectl expose deployment my-nginx --port 8085 --target-port 80 # Your pod name will be different. Use kubectl get pods to get pod name.
```

 </div>
</details>

## Do a request a to a service

Now that we have two services, they can reach each other. From your original pod, let's see if we can reach the new nginx (which is a web server). To do that, we have to get into the running docker container in the pod and run curl. The steps are:

`kubectl exec -it sample-app-6755d84575-gdmw8 sh`

Your name will be a bit different, use `kubectl get po` to find your pod.

We're now in a shell inside the container in the pod. Let's finally try to reach the service we just exposed:

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

In other words, you were able to do a HTTP request to `nginx`, which were resolved by the Kubernetes DNS.

## Do a request the other way around

We just did a request from our own service to the nginx-service. Let's do it the other way around.

```
kubectl exec -it my-nginx-7d4b689dcb-2j57k sh
curl -vvv http://sample-app:8085 # To check the name of your service and the port, run: kubectl get svc
```

It should produce whatever welcome page that is in the sample app.

By the way, when we fire up a new pod, all services in the same namespace are injected as environmental variables in the pod's containers. To see this, run (in the same pod as above):

```
env | sort
```

This should yield something similar like this:

```
HOME=/root
HOSTNAME=my-nginx-7d4b689dcb-2j57k
KUBERNETES_PORT=tcp://10.96.0.1:443
KUBERNETES_PORT_443_TCP=tcp://10.96.0.1:443
KUBERNETES_PORT_443_TCP_ADDR=10.96.0.1
KUBERNETES_PORT_443_TCP_PORT=443
KUBERNETES_PORT_443_TCP_PROTO=tcp
KUBERNETES_SERVICE_HOST=10.96.0.1
KUBERNETES_SERVICE_PORT=443
KUBERNETES_SERVICE_PORT_HTTPS=443
MY_NGINX_PORT=tcp://10.99.107.184:8085
MY_NGINX_PORT_8085_TCP=tcp://10.99.107.184:8085
MY_NGINX_PORT_8085_TCP_ADDR=10.99.107.184
MY_NGINX_PORT_8085_TCP_PORT=8085
MY_NGINX_PORT_8085_TCP_PROTO=tcp
MY_NGINX_SERVICE_HOST=10.99.107.184
MY_NGINX_SERVICE_PORT=8085
NGINX_VERSION=1.13.12-1~stretch
NJS_VERSION=1.13.12.0.2.0-1~stretch
PATH=/usr/local/sbin:/usr/local/bin:/usr/sbin:/usr/bin:/sbin:/bin
PWD=/
SOURCE_APP_PORT=tcp://10.101.107.43:8085
SOURCE_APP_PORT_8085_TCP=tcp://10.101.107.43:8085
SOURCE_APP_PORT_8085_TCP_ADDR=10.101.107.43
SOURCE_APP_PORT_8085_TCP_PORT=8085
SOURCE_APP_PORT_8085_TCP_PROTO=tcp
SOURCE_APP_SERVICE_HOST=10.101.107.43
SOURCE_APP_SERVICE_PORT=8085
TERM=xterm
```

## Task: Do a request to a service in another namespace

Just pick any of the other students' services, or use one that we should (but may have forgotton) to deploy in the namespace `sample-namespace`. To find services in other namespaces, run:

`kubectl get svc --all-namespaces`

<details>
	<summary>Solution</summary>
	<div markdown="1">

```
# Pick any pod in your namespace to do the request from
kubectl exec -it my-nginx-7d4b689dcb-2j57k sh

# Now in the pod, run
$ curl http://hello-nginx.sample-namespace:8086
```

Then you should a reply from the nginx welcome page.

</div>
</details>
