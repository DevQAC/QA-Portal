#!/bin/bash
kubectl apply -f ../jenkins/persistent-volume-jenkins.yaml
kubectl apply -f ../jenkins/pod-jenkins.yaml
kubectl apply -f ../jenkins/service-jenkins.yaml

kubectl apply -f ../nginx/config-map.yaml
kubectl apply -f ../nginx/deployment-nginx.yaml
kubectl apply -f ../nginx/service-nginx.yaml
