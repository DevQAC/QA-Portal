FROM jenkins/jenkins:lts

USER root
RUN apt-get update
RUN apt-get install -y sudo

# docker
RUN curl https://get.docker.com | bash

RUN usermod -aG docker jenkins
RUN usermod -aG docker $(whoami)
RUN newgrp docker

# docker-compose
RUN curl -L "https://github.com/docker/compose/releases/download/1.24.0/docker-compose-$(uname -s)-$(uname -m)" -o /usr/local/bin/docker-compose
RUN chmod +x /usr/local/bin/docker-compose

# kubectl
RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
RUN chmod +x ./kubectl
RUN sudo mv ./kubectl /usr/local/bin/kubectl

# sudo install and configuration
RUN echo 'jenkins ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers
USER jenkins

USER root

#gcp install
RUN curl -O https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-258.0.0-linux-x86_64.tar.gz
RUN tar zxvf google-cloud-sdk-258.0.0-linux-x86_64.tar.gz google-cloud-sdk

RUN  ./google-cloud-sdk/install.sh

#RUN ./google-cloud-sdk/bin/gcloud components install kubectl docker-credential-gcr
#RUN ./google-cloud-sdk/bin/gcloud auth configure-docker

RUN chmod 777 /google-cloud-sdk/bin

ENV PATH $PATH:/google-cloud-sdk/bin
RUN gcloud components install kubectl docker-credential-gcr
RUN gcloud auth configure-docker

USER jenkins
