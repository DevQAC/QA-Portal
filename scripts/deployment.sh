#!/bin/bash
kubectl apply -f ../persistent-volume-claim.yaml
kubectl apply -f ../pod-postgres.yaml
kubectl apply -f ../service-postgres.yaml

kubectl apply -f ../pod-keycloak.yaml
kubectl apply -f ../service-keycloak.yaml

kubectl apply -f ../qa-portal-services/core-api/deployment.yaml
kubectl apply -f ../qa-portal-services/core-api/service.yaml

kubectl apply -f ../qa-portal-services/self-reflection/deployment.yaml
kubectl apply -f ../qa-portal-services/self-reflection/service.yaml

kubectl apply -f ../qa-portal-angular/deployment.yaml
kubectl apply -f ../qa-portal-angular/service.yaml
