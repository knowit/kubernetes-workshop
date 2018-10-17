---
title: Helm
permalink: /docs/helm/
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

To enable autocomplete

`source <(helm completion bash) # bash is the standard shell on macOS and Linux. can be changed to zsh`


## Task: Setup Helm and install a chart

We do not host our own charts repository, but you should be able to install a chart
from the git repo.

To start off we need to setup helm:

```
helm init --client-only
```

Then install a chart from our git repo:

```
cd apps/helm_charts
helm install workshop-app
```

Let's look at the running charts:

```
$ helm ls
NAME            REVISION  UPDATED       STATUS    CHART              APP VERSION NAMESPACE         
virtuous-alpaca 1         Wed Oct 1...  DEPLOYED  workshop-app-0.1.0 1.0         yngvar-kristiansen
```

When you're done, delete the chart:

`helm delete --purge virtuous-alpaca`

## Repository browser

There's a bunch of ready charts at <http://kubeapps.com>:

![text](../../assets/img/kubeapps.png)
