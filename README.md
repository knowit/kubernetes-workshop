# Kubernetes workshop

## Install kubectl configuration

Someone will supply you with a configuration file.

Place it in a suitable location, such as `~/.kube/workshop_config`.

Point the environment variable `KUBECONFIG` to your configuration

`export KUBECONFIG=$HOME/.kube/workshop_config`

Check with kubectl that the configuration works

`kubectl config view`
