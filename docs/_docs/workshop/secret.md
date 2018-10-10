---
title: Secrets
permalink: /docs/secret/
---
{% assign host = site.data.constants.docs.k8s.base-url %}
{% assign path = '/docs/concepts/configuration/secret/' %}
Secret documentation:
* [Offline]({{host.offline}}{{path}})
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
kubectl --namespace=mynamespace create secret generic mysecret --from-file=./supersecret.txt
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
        image: ubuntu-k8s-1.local:30603/sample-app
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

  </div>
</details>
