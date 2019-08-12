pipeline {
    agent any

    stages {
        stage('Clone repo')
            steps{
                git branch: 'ci-kube-dimi', url: 'https://github.com/bob-crutchley/QA-Portal.git'
            }
        stage('Install gcloud')
            steps{
                sh label: '', script: 'echo "deb [signed-by=/usr/share/keyrings/cloud.google.gpg] http://packages.cloud.google.com/apt cloud-sdk main" | sudo tee -a /etc/apt/sources.list.d/google-cloud-sdk.list'
                sh label: '', script: 'curl https://packages.cloud.google.com/apt/doc/apt-key.gpg | sudo apt-key --keyring /usr/share/keyrings/cloud.google.gpg add -'
                sh label: '', script: 'sudo apt-get update && sudo apt-get install google-cloud-sdk'
            }



        stage('Build') {
            steps {
                echo 'Building..'
            }
        }
        stage('Test') {
            steps {
                echo 'Testing..'
            }
        }
        stage('Deploy') {
            steps {
                echo 'Deploying....'
            }
        }
    }
}
