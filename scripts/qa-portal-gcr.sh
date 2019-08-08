#!/bin/bash

docker tag jpeckover/core-api:latest eu.gcr.io/johnny-20091993/qa-portal-services-core-api:latest
docker tag jpeckover/self-reflection:latest eu.gcr.io/johnny-20091993/qa-portal-services-self-reflection:latest
docker tag jpeckover/user-api:latest eu.gcr.io/johnny-20091993/qa-portal-services-user-api:latest
docker tag yamileon/qa-ang eu.gcr.io/johnny-20091993/qa-portal-angular:latest


docker push eu.gcr.io/johnny-20091993/qa-portal-services-core-api:latest
docker push eu.gcr.io/johnny-20091993/qa-portal-services-self-reflection:latest
docker push eu.gcr.io/johnny-20091993/qa-portal-services-user-api:latest
docker push eu.gcr.io/johnny-20091993/qa-portal-angular:latest
