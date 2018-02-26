#!/usr/bin/env groovy

node {
    stage ("Checkout") {
        checkout scm
    }

    stage ('Clean') {
        mvn clean
    }

    stage('Test Coverage') {
        try {
            mvn org.jacoco:jacoco-maven-plugin:prepare-agent install
        } finally {
            junit '**/target/surefire-reports/TEST-*.xml'

            if (currentBuild.result == 'UNSTABLE') {
                currentBuild.result = 'FAILURE'
            }
        }
    }

    stage ('SonarQube Analysis') {
        withSonarQubeEnv('sonarqube') {
            mvn sonar:sonar
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