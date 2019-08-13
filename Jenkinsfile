pipeline {
    agent any
    stages {
        stage('pull images' {
            steps {
                sh label: '', script: 'bash ./scripts/pull-gcr.sh.sh'
            }
        }

        stage('Deploy') {
            steps {
                sh label: '', script: 'bash ./scripts/deployment.sh'
            }
        }
    }
}
