#!/bin/bash

kubectl delete deployments --all
kubectl delete --all pods
kubectl delete services --all

kubectl get all
