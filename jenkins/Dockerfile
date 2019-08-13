FROM jenkins/jenkins:alpine
USER root

RUN apk update

#some basic utils
RUN apk add curl sudo jq
RUN apk add docker
# docker-compose
RUN apk add py-pip
RUN apk add python-dev libffi-dev openssl-dev gcc libc-dev make
RUN pip install docker-compose

# kubectl
#RUN curl -LO https://storage.googleapis.com/kubernetes-release/release/$(curl -s https://storage.googleapis.com/kubernetes-release/release/stable.txt)/bin/linux/amd64/kubectl
#RUN chmod +x ./kubectl
#RUN sudo mv ./kubectl /usr/local/bin/kubectl

#RUN apk del python-dev libffi-dev openssl-dev gcc libc-dev make
#RUN apk del py-pip

# sudo install and configuration
#RUN apt install -y sudo
RUN echo 'jenkins ALL=(ALL) NOPASSWD:ALL' >> /etc/sudoers



ENV PATH /google-cloud-sdk/bin:$PATH
RUN apk --no-cache add python py-crcmod libc6-compat
RUN curl -O https://dl.google.com/dl/cloudsdk/channels/rapid/downloads/google-cloud-sdk-257.0.0-linux-x86_64.tar.gz
RUN tar xzf google-cloud-sdk-257.0.0-linux-x86_64.tar.gz
RUN bash google-cloud-sdk/install.sh

RUN gcloud components install kubectl docker-credential-gcr

RUN gcloud auth configure-docker

