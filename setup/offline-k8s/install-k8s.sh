#!/bin/bash

# Docker 
apt-get update &&\
apt-get install -y docker.io &&\
#kubeadm, kubelet and kubectl
apt-get update && apt-get install -y apt-transport-https curl &&\
curl -s https://packages.cloud.google.com/apt/doc/apt-key.gpg | apt-key add - &&\
cat <<EOF >/etc/apt/sources.list.d/kubernetes.list
deb http://apt.kubernetes.io/ kubernetes-xenial main
EOF
apt-get update &&\
apt-get install -y kubelet kubeadm kubectl &&\
# docker cgroup
docker info | grep -i cgroup &&\
cat /etc/systemd/system/kubelet.service.d/10-kubeadm.conf &&\
sed -i "s/cgroup-driver=systemd/cgroup-driver=cgroupfs/g" /etc/systemd/system/kubelet.service.d/10-kubeadm.conf &&\
systemctl daemon-reload &&\
systemctl restart kubelet
