pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git branch: 'main', url: 'https://github.com/harshithaaa2727/talend-job'
            }
        }

        stage('Run Talend Job') {
            steps {
                bat 'job_run.bat'
            }
        }
    }
}
