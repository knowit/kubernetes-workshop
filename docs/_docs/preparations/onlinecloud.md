---
title: Requirements (online cloud (GCP))
permalink: /docs/requirements-online/
---

## Install gcloud

Follow the instructions at https://cloud.google.com/sdk/

## Install kubectl

Follow the instructions at https://kubernetes.io/docs/tasks/tools/install-kubectl/

## Connect to our cluster and install kubectl configuration

* Go to https://console.cloud.google.com/
* Make sure your logged in user is your Knowit account (see user icon in upper right)
* Open a terminal, and run `gcloud auth login`. Follow the instructions, and use your Knowit account.
* Click "Select a project" in the top, type "kubernetes-workshop-101" and select it.
* In the navigation menu on the left, go to Kubernetes Engine (under "Compute") -> Clusters. Verify that you
can see a cluster (i.e. you can see for instance cluster-1, europe-west3-a, 6 vCPUS, and so on). If you
cannot, it probably means we haven't given you access. Ask one of your workshop holders.
* Click `Connect` next to the cluster and copy-paste the command under `Command-line access` into a terminal. Execute the command.

![google cloud project screenshot](../../assets/img/gcloud-project.png)

You will have been granted access to a namespace (more on namespaces later) on the form firstname-lastname, the same names as in your knowit-email-address.

To avoid having to specify the namespace in all requests with kubectl, set the namespace preference in the config

`kubectl config set-context $(kubectl config current-context) --namespace=<insert-namespace-name-here>`

## Install helm client

Follow the instructions at https://github.com/helm/helm/blob/master/docs/install.md#installing-the-helm-client
