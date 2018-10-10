#!/bin/bash
EMAIL=$1
DASH=$(echo ${EMAIL/./-} | cut -d '@' -f 1)

kubectl create ns $DASH &&\
kubectl -n $DASH create rolebinding $DASH --clusterrole=cluster-admin --user=$EMAIL
