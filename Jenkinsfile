
pipeline {
    agent any

    stages {
        stage('Clone repo')
            steps{
                git branch: 'ci-kube-build', url: 'https://github.com/bob-crutchley/QA-Portal.git'
            }
        stage('docker-compose build') {
            steps {
                sh label: '', script: 'docker-compose build .'
            }
        }
        stage('Push to GCR') {
            steps {
                sh label: '', script: 'bash ./scripts/qa-portal-gcr.sh'
            }
        }
    }
}