#!/usr/bin/env groovy

node {
    stage ("Checkout") {
        checkout scm
    }

    stage ('Clean') {
        sh 'make clean'
    }

    stage('Test Coverage') {
        try {
            sh 'make maven.test-coverage'
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'

            if (currentBuild.result == 'UNSTABLE') {
                currentBuild.result = 'FAILURE'
            }
        }
    }

    stage ('SonarQube Analysis') {
        withSonarQubeEnv('sonarqube') {
            sh 'make sonar'
        }
    }

    stage ('SonarQube Quality Gate') {
        timeout(5) {
            def qg = waitForQualityGate()
            if (qg.status != 'OK') {
                error "SonarQube Quality Gate: ${qg.status}"
            }
        }
    }
}