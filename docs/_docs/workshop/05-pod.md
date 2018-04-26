---
title: 05 - Pod
permalink: /docs/05-pod/
---

See http://192.168.1.71:30827/docs/concepts/workloads/pods/pod/

![text](../../assets/img/pods.png)

A Pod is a group of docker (or other) containers. Think of a pod as a microservice. A microservice can for
instance consist of two containers, one for app logic, and one container for MySQL (for instance).

To show all pods running in your Kubernetes cluster:

```
kubectl get po --all-namespaces
```

Containers within a pod share an IP address and port space, and can find each other via localhost.

So in your app, your Java code could connect to MySQL on "jdbc:mysql://localhost:3306/db".

## YAML

Pods and other resources in Kubernetes can be described in YAML. Here is the YAML for a Pod:

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: myapp-pod
  labels:
    app: myapp
spec:
  containers:
  - name: myapp-container
    image: busybox
    command: ['sh', '-c', 'echo Hello Kubernetes! && sleep 3600']
```

Now, let's finally deploy this pod to our cluster, so we can get something up and running!

## Task: Deploy the pod

```
# Watch running pods
watch kubectl get po

# Open a new terminal and run the pod
git clone git@github.com:knowit/kubernetes-workshop.git
cd kubernetes-workshop/apps/sample-apps/sample-app/k8s
kubectl apply -f pod.yaml
```

Switch to the watch terminal, and you should see your pod running! It should like this:

```
NAME         READY     STATUS    RESTARTS   AGE
sample-app   1/1       Running   0          48s
```

## Task: Access the app

OK, it's deployed, so let's access our app, i.e. open it in a web browser.

We're still not educated enough (yet) to make it accessible a proper way, but we can access it by running some port forwarding
magic:

```
kubectl port-forward sample-app 8080:8080
```
Now you should be able to go to http://localhost:8080/ in your web browser and see some results.

## Task: Check the logs

If something fails or whatever other reason, you can check your app's logs by doing:

```
kubectl logs -f sample-app
```

## Task: Delete the pod

```
kubectl delete -f pod.yaml
```
