#!/bin/bash

kubectl create -f qa-portal-services/core-api/deployment.yaml
kubectl create -f qa-portal-services/core-api/service.yaml

kubectl apply -f qa-portal-services/core-api/deployment.yaml
kubectl apply -f qa-portal-services/core-api/service.yaml

kubectl create -f qa-portal-services/self-reflection/deployment.yaml
kubectl create -f qa-portal-services/self-reflection/service.yaml

kubectl apply -f qa-portal-services/self-reflection/deployment.yaml
kubectl apply -f qa-portal-services/self-reflection/service.yaml

kubectl create -f qa-portal-services/user-api/deployment.yaml
kubectl create -f qa-portal-services/user-api/service.yaml

kubectl apply -f qa-portal-angular/deployment.yaml
kubectl apply -f qa-portal-angular/service.yaml


