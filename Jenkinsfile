pipeline {
    agent any

    stages {
        stage('Checkout') {
            steps {
                // Checkout code from your GitHub repo
                git url: 'https://github.com/harshithaaa2727/talend-job.git', branch: 'main'
            }
        }

        stage('Run Talend Job') {
            steps {
                script {
                    // Run your Talend job batch file
                    bat 'job/job_run.bat'
                }
            }
        }
    }
}
