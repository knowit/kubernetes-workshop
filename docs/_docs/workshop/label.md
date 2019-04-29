---
title: Label
permalink: /docs/label/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/overview/working-with-objects/labels/' %}
Label documentation:
{% comment %}
* [Offline]({{host.offline}}{{path}})
{% endcomment %}
* [Online]({{host.online}}{{path}})

Labels are key value pairs that describe resources in kubernetes. Labels should be used to identify and describe useful and important attributes for the resources.

Example of some good labels that describe some useful properties about a pod:
- `environment`:`dev`, `environment`:`prod`
- `version`:`v10.2`, `version`:`v11.0`

The reason we want to assign proper labels to our resources (pods especially) is to utilise the various `selectors` that are specified in many control resources.

A selector defines a set of labels and values, and tries to select the resources that matches the defined selector.

For example the selector in `Deployment` defines its selector as:

```YAML
selector:
  matchLabels:
    app: sample-app
```
Which will match each pod with the label `app`:`sample-app`.

> To view the labels of a resource deployed to the cluster use the extra flag `--show-labels`. E.g. `kubectl get po --show-labels`

## Task 1
Deploy the Pod from the previous section again.
Edit the pod using `kubectl edit`, and change the label. Verify by getting the pods and using `--show-labels`. Alternatively you can change the yaml file before redeploying. Try both ways!

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: Labels and pods

 - `kubectl edit deployment [deployment-name]` opens a editor. Change the label there.
 - Or just edit the .yaml file in your favourite editor before redeploying.
 - Verify by `kubectl get pod --show-labels`

 ```yaml
 kind: Pod
 metadata:
   name: myapp-pod
   labels:
     newLabel: helloWorld
```

 </div>
</details>
