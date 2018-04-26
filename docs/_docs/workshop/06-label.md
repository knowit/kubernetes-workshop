---
title: 06 - Label
permalink: /docs/06-label/
---

Labels are key value pairs that describe resources in kubernetes. Labels should be used to identify and describe useful and important attributes for the resources.

Example of some good labels that describe some useful properties about a pod:
- `environment`:`dev`, `environment`:`prod`
- `version`:`v10.2`, `version`:`v11.0`

The reason we want to assign proper labels to our resources (pods especially) is to utilise the various `selectors` that are specified in many control resources.

A selector defines a set of labels and values, and tries to select the resources that matches the defined selector.

For example the selector in `Deployment` (which is the next topic) defines its selector as:

```YAML
selector:
  matchLabels:
    app: sample-app
```
Which will match each pod with the label `app`:`sample-app`.

> To view the labels of a resource deployed to the cluster use the extra flag `--show-labels`. E.g. `kubectl get po --show-labels`

## Task 1

Edit a pod, and change the label. Verify by using the flag `--show-labels`

<details>
 <summary>Solution</summary>
 <div markdown="1">

### Solution: Try to port forward

- `kubectl port-forward [pod-name] --namespace [namespace]`

 </div>
</details>
