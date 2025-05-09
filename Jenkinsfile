pipeline {
    agent any
    tools {
        maven 'Maven 3.9.0'
        jdk 'JDK17'
    }

    stages {
        stage('Checkout') {
            steps {
                git 'https://github.com/longk15t/CBTWTest.git'
            }
        }
        stage('Build and test') {
            steps {
                cmd 'mvn clean test'
            }
        }
        stage('Publish report') {
            steps {
                junit 'target/surefire-reports/*.xml'
            }
        }
    }
}