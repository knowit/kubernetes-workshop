---
title: 07 - Deployment
permalink: /docs/07-deployment/
---

A Deployment is the result of someone deploying a Pod to the cluster. 

A couple of useful features of a Deployment is:
* A deployment ensures that your pod always is running. If it crashes or stops responding, Kubernetes will
deploy a new pod and kill the old one.
* You can scale up your app to handle more load. For instance, you can set the number of replications of a pod
to 3. Then Kubernetes will create 3 copies of your pod (and ensure that they're always running), and then load
balance every request between those 3 pods.
* You can rollback a deployment to a previous version.

## Task

Create a deployment of our sample app.

## Solution

## Extra task if you have a lot of time

Do the following uses cases described in Kubernetes documentation of Deployments (title "Use Case"):

http://192.168.1.71:30827/docs/concepts/workloads/controllers/deployment/
