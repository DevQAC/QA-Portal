#!/bin/bash
sudo docker tag core-api:latest eu.gcr.io/$PROJECT/qa-portal-services-core-api:latest
sudo docker tag self-reflection:latest eu.gcr.io/$PROJECT/qa-portal-services-self-reflection:latest
sudo docker tag user-api:latest eu.gcr.io/$PROJECT/qa-portal-services-user-api:latest
sudo docker tag portal-core eu.gcr.io/$PROJECT/qa-portal-core:latest

sudo docker push eu.gcr.io/$PROJECT/qa-portal-services-core-api:latest
sudo docker push eu.gcr.io/$PROJECT/qa-portal-services-self-reflection:latest
sudo docker push eu.gcr.io/$PROJECT/qa-portal-services-user-api:latest
sudo docker push eu.gcr.io/$PROJECT/qa-portal-angular:latest