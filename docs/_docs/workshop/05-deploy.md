---
title: 05 - Deployment
permalink: /docs/05-deployment/
---

# Deployment

A Deployment is the result of someone deploying a Pod to the cluster. 

A couple of useful features of a Deployment is:
* You can scale up your app to handle more load. For instance, you can set the number of replications of a pod
to 3. Then Kubernetes will create 3 copies of your pod, and then load balance every request between those 3
pods. 
* You can rollback a deployment

# Task

Create a deployment of our sample app.

# Solution



# Extra task if you have a lot of time

Do the following uses cases described in Kubernetes documentation of Deployments (title "Use Case"):

http://192.168.1.71:30827/docs/concepts/workloads/controllers/deployment/
