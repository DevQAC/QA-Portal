#!/bin/bash
kubectl create -f persistent-volume-claim.yaml
kubectl create -f pod-postgres.yaml
kubectl create -f service-postgres.yaml

kubectl create -f pod-keycloak.yaml
kubectl create -f service-keycloak.yaml
