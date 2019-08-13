#!/bin/bash
$PROJECT=PLACEHOLDER

docker pull eu.gcr.io/$PROJECT/qa-portal-services-core-api:latest
docker pull eu.gcr.io/$PROJECT/qa-portal-services-self-reflection:latest
docker pull eu.gcr.io/$PROJECT/qa-portal-services-user-api:latest
docker pull eu.gcr.io/$PROJECT/qa-portal-angular:latest