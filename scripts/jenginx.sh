#!/bin/bash
kubectl apply -f ../jenkins/persistent-volume-jenkins.yaml
kubectl apply -f ../jenkins/service-account.yaml
kubectl apply -f ../jenkins/cluster-role.yaml
kubectl apply -f ../jenkins/cluster-role-binding.yaml
kubectl apply -f ../jenkins/pod-jenkins.yaml
kubectl apply -f ../jenkins/service-jenkins.yaml

kubectl apply -f ../nginx/config-map.yaml
kubectl apply -f ../nginx/deployment-nginx.yaml
kubectl apply -f ../nginx/service-nginx.yaml

echo "kubectl exec -it jenkins cat /var/jenkins_home/secrets/initialAdminPassword"

