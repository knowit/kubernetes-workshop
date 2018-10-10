---
title: 04 - Helm
permalink: /docs/04-helm/
---

Kubernetes Helm is a package manager for Kubernetes. With helm you can simply do

```
helm install stable/mongodb
```

to deploy the MongoDB in the current namespace. A package in Helm is called a Chart, by the way.

Without helm, you would need to run `kubectl apply -f .` in a directory containing all the YAML files needed
for your application to run. And if you want to deploy your app to different namespaces, but with slightly
different configuration, you would need one YAML file per configuration. In contrast, in Helm you can do

```
helm install stable/mongodb --set mongodbUsername=root --set mongodbPassword=foo
```

or

```
helm install stable/mongodb -f values.yaml
```

to configure the Mongodb chart.

## Task: Setup Helm and install a chart

We do not host our own charts repository, but you should be able to install a chart
from the git repo.

To start off we need to setup helm:

`helm init --client-only`

From the workdir, navigate to the helm chart folder:
 `cd apps/helm-charts`

To install a helm chart you should run [helm upgrade](https://docs.helm.sh/helm/helm_upgrade/):

`helm upgrade --install [helm-chart-dir]`
> Note: helm-chart-dir should be a dir in `helm-charts`

Let's look at the running charts:

```
$ helm ls
NAME                  REVISION  UPDATED                   STATUS          CHART             NAMESPACE  
original-bird         1         Thu Apr 26 19:08:52 2018  DEPLOYED        nginx-0.1.0       yngvar     
```

When you're done, delete the chart:

`helm delete --purge original-bird`

## Repository browser

There's a bunch of ready charts at http://kubeapps.com:

![text](../../assets/img/kubeapps.png)
