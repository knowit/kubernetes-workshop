---
title: 08 - Deployment
permalink: /docs/08-deploy/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/workloads/controllers/deployment/#use-case' %}
Deployment documentation:
* [Offline]({{host.offline}}{{path}})
* [Online]({{host.online}}{{path}})


![text](../../assets/img/deployment.png)

A Deployment is the result of someone deploying a Pod to the cluster. 

A couple of useful features of a Deployment is:
* A deployment ensures that your pod always is running. If it crashes or stops responding, Kubernetes will
deploy a new pod and kill the old one.
* You can scale up your app to handle more load. For instance, you can set the number of replications of a pod
to 3. Then Kubernetes will create a total of three copies of your pod (and ensure that they're always running), and then load
balance every request between those 3 pods.
* You can rollback a deployment to a previous version.

## YAML

A deployment looks like this:

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: # Some name
  labels:
    app: sample-app
spec:
  replicas: # Number of replicas here
  selector:
    matchLabels:
      app: # Put a label here for your app
  template:
    metadata:
      labels:
        app: # Put the same label here
    spec:
      containers:
      - name: sample-app
        image: ubuntu-k8s-1.local:30603/sample-app
        imagePullPolicy: Always
        ports:
        - containerPort: 8080

```

## Task: Create deployment

Create a deployment using the pod from the previous steps.

<details>
 <summary>Solution</summary>
 <div markdown="1">

```
# Watch results
watch kubectl get deployment

# Put the content in the YAML below into a file, my_deployment.yaml
kubectl apply -f my_deployment.yaml
```
Switch back to first terminal, and observe that the deployment is created.

```yaml
apiVersion: apps/v1
kind: Deployment
metadata:
  name: sample-app-deployment
  labels:
    app: sample-app
spec:
  replicas: 1
  selector:
    matchLabels:
      app: sample-app
  template:
    metadata:
      labels:
        app: sample-app
    spec:
      containers:
      - name: sample-app
        image: ubuntu-k8s-1.local:30603/sample-app
        imagePullPolicy: Always
        ports:
        - containerPort: 8080

```

 </div>
</details>


## Task: Delete a pod in the deployment

* Delete a pod in the deployment you just created.
* Watch it get recreated automatically.

<details>
 <summary>Solution</summary>
 <div markdown="1">


Stop the `watch` command from above, and instead run

```
watch kubectl get po
```

Now, this command should output something like:


```
NAME                                     READY     STATUS    RESTARTS   AGE
sample-app-deployment-7776c6b654-sshv8   1/1       Running   0          1m
```

Delete the listed pod. In this example, the command is:

```
kubectl delete po sample-app-deployment-7776c6b654-sshv8
```

and watch the `watch` output.

You should see something like this:

```
NAME                                    READY     STATUS              RESTARTS   AGE
sample-app-deployment-864bcb76f-9c7gn   0/1       Terminating         0          1m
sample-app-deployment-864bcb76f-pg9jk   0/1       ContainerCreating   0          <invalid>
```

Awesome, Kubernetes is auto creating a new pod since the first was killed.

 </div>
</details>

## Task: Create 3 replicas

In `my_deployment.yaml`, modify `replicas: 1` to `replicas: 3`. Then do:

```
kubectl apply -f my_deployment.yaml
```

Watch the `watch` output, and enjoy watching Kubernetes scaling up our app!
