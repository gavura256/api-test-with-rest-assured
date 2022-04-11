pipeline {
    agent any
    tools {
        maven 'Maven 3.8.4'
        jdk 'jdk17'
    }
    stages {
         stage ('Build') {
            steps {
                sh 'mvn clean test'
            }
        }
    }
}