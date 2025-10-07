pipeline {
    agent any
    tools { jdk 'jdk21' }
    stages {
        stage('Checkout') {
            steps { checkout scm }
        }
        stage('Build & Test') {
            steps {
                sh 'mvn -version'
                sh 'mvn -B clean test'
            }
            post {
                always { archiveArtifacts artifacts: 'reports/**', fingerprint: true }
            }
        }
    }
}



