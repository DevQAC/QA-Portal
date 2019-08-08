#!/bin/bash
kubectl apply -f ../persistent-volume-claim.yaml
kubectl apply -f ../pod-postgres.yaml
kubectl apply -f ../service-postgres.yaml

kubectl apply -f ../pod-keycloak.yaml
kubectl apply -f ../service-keycloak.yaml
