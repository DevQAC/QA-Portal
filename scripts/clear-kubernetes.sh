#!/bin/bash
kubectl delete pod --all
kubectl delete service --all
kubectl delete pvc --all
kubectl delete configmap --all

