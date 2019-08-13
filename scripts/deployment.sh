#!/bin/bash
kubectl apply -f ../nginx/config-map.yaml
kubectl apply -f ../nginx/deployment-nginx.yaml
kubectl apply -f ../nginx/service-nginx.yaml

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

kubectl apply -f ../jenkins/persistent-volume-jenkins.yaml
kubectl apply -f ../jenkins/service-acccount.yaml
kubectl apply -f ../jenkins/cluster-role.yaml
kubectl apply -f ../jenkins/cluster-role-binding.yaml
kubectl apply -f ../jenkins/pod-jenkins.yaml
kubectl apply -f ../jenkins/service-jenkins.yaml