pipeline {
    agent any

    stages {
        stage('Clone Repo') {
            steps {
                git branch: 'main', url: 'https://github.com/harshithaaa2727/talend-job.git'
            }
        }

        stage('Unzip Talend Job') {
            steps {
                bat 'powershell -Command "Expand-Archive -Force job_0.1.zip job_unzipped"'
            }
        }

        stage('Run Talend Job') {
            steps {
                dir('job_unzipped') {
                    bat 'runJob.bat' // Change if your Talend batch script name is different
                }
            }
        }
    }
}
