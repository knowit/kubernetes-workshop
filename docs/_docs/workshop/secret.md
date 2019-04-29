---
title: Secrets
permalink: /docs/secret/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/configuration/secret/' %}
Secret documentation:
{% comment %}
* [Offline]({{host.offline}}{{path}})
{% endcomment %}
* [Online]({{host.online}}{{path}})


Objects of type secret are intended to hold sensitive information, such as passwords, OAuth tokens, and ssh keys.

Secrets can be mounted into files in the container or used as environment variables.

Secrets are base64 encoded and access control can be enabled so that not all users are able to view the secrets.


## Task

Create a secret and mount it into a file in the container at ***/secrets/supersecret.txt***. The endpoint ***/secret*** in the sample app can be used to view the secret.

<details>
  <summary>Solution</summary>
  <div markdown="1">

### Solution, Creating the secret from a file

```bash
echo -n 'my_supersecret' > ./supersecret.txt
kubectl create secret generic mysecret --from-file=./supersecret.txt
```

### Solution, Mounting the secret into a file in the container

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
        image: torklo/workshop-frontend
        imagePullPolicy: Always
        ports:
        - containerPort: 8080
        volumeMounts:
        - name: greatestconfig
          mountPath: /config/greatestconfig.yml
          readOnly: true
          subPath: greatestconfig.yml
        - name: supersecret
          mountPath: "/secrets"
          readOnly: true
      volumes:
      - name: supersecret
        secret:
          secretName: mysecret
      - name: greatestconfig
        configMap:
          defaultMode: 0600
          name: myconfigmap
```

Now open a shell in your pod and check if there are any secrets there

```
kubectl exec -it [pod-name] sh
cat /secrets/*
```

If the pod won't start, it may be because two pods tries to mount a PVC that is only supposed to be access by one PVC. You can use `kubectl get event` to confirm this:

```
52s         52s          1       workshop-api-deployment-9c4cfc4c6-c5wgx.1599fdece10a7b4f    Pod                                                          Warning   FailedAttachVolume       attachdetach-controller                                      Multi-Attach error for volume "pvc-c3d75a68-6a96-11e9-8cf6-42010aa601e4" Volume is already used by pod(s) workshop-api-deployment-5cf49764b9-75dct
```

To fix this, delete any of the running pods manually.


  </div>
</details>
