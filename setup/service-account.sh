#!/bin/bash

#Create the service account
gcloud iam service-accounts create jenkins --display-name jenkins

echo " Creating service account..."
sleep 30

#Store the service account email address and your 
#current GCP project ID in environment variables for use in later commands:
export SA_EMAIL=$(gcloud iam service-accounts list \
    --filter="displayName:jenkins" --format='value(email)')
export PROJECT=$(gcloud info --format='value(config.project)')

#Bind the following roles to your service account
gcloud projects add-iam-policy-binding $PROJECT \
    --role roles/storage.admin --member serviceAccount:$SA_EMAIL
gcloud projects add-iam-policy-binding $PROJECT --role roles/compute.instanceAdmin.v1 \
    --member serviceAccount:$SA_EMAIL
gcloud projects add-iam-policy-binding $PROJECT --role roles/compute.networkAdmin \
    --member serviceAccount:$SA_EMAIL
gcloud projects add-iam-policy-binding $PROJECT --role roles/compute.securityAdmin \
    --member serviceAccount:$SA_EMAIL
gcloud projects add-iam-policy-binding $PROJECT --role roles/iam.serviceAccountActor \
    --member serviceAccount:$SA_EMAIL

# Create the key file:
gcloud iam service-accounts keys create ~/jenkins-sa.json --iam-account $SA_EMAIL


echo " "
echo "*****************************************************"
echo " "
echo "jenkins-sa.json key is located in home directory"
echo " "
echo "*****************************************************"
echo " "
