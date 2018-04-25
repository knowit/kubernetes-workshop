---
title: 13 - Secret
permalink: /docs/13-secret/
---

# Secret

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
kubectl create secret generic mysecret --from-file=./supersecret.txt
```

### Solution, Mounting the secret into a file in the container

```yaml
apiVersion: v1
kind: Pod
metadata:
  name: mypod
spec:
  containers:
  - name: mypod
    image: redis
    volumeMounts:
    - name: supersecret
      mountPath: "/secrets/supersecret.txt"
      readOnly: true
  volumes:
  - name: supersecret
    secret:
      secretName: mysecret
```

</details>
