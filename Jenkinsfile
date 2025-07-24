pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/harshithaaa2727/talend-job'
            }
        }

        stage('Run Talend Job') {
            steps {
                bat 'job\\job_run.bat'
            }
        }
    }
}
