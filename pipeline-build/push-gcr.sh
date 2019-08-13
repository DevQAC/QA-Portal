#!/bin/bash
$PROJECT=PLACEHOLDER


docker tag core-api:latest eu.gcr.io/$PROJECT/qa-portal-services-core-api:latest
docker tag self-reflection:latest eu.gcr.io/$PROJECT/qa-portal-services-self-reflection:latest
docker tag user-api:latest eu.gcr.io/$PROJECT/qa-portal-services-user-api:latest
docker tag portal-core eu.gcr.io/$PROJECT/qa-portal-core:latest


docker push eu.gcr.io/$PROJECT/qa-portal-services-core-api:latest
docker push eu.gcr.io/$PROJECT/qa-portal-services-self-reflection:latest
docker push eu.gcr.io/$PROJECT/qa-portal-services-user-api:latest
docker push eu.gcr.io/$PROJECT/qa-portal-angular:latest