pipeline {
    agent any
    stages {
        stage('Clone repo')
            steps{
                git branch: 'ci-kube-deploy', url: 'https://github.com/bob-crutchley/QA-Portal.git'
            }

        stage('Deploy') {
            steps {
                sh label: '', script: 'bash ./scripts/deployment.sh'
            }
        }
    }
}
