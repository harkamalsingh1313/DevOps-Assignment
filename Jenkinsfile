pipeline {
    agent any

    environment {
        // Name of your SonarQube server configured in Jenkins (Manage Jenkins -> Configure System)
        SONARQUBE_SERVER = 'MySonarQubeServer'
        // Path to Maven if needed (e.g., /usr/share/maven)
        MAVEN_HOME = '/usr/share/maven'
    }

    stages {

        stage('Checkout') {
            steps {
                echo "Checking out the source code..."
                checkout scm
            }
        }

        stage('Build & Unit Tests') {
            steps {
                echo "Building the project and running unit tests..."
                sh "${MAVEN_HOME}/bin/mvn clean install"
            }
        }

        stage('Code Quality Analysis - SonarQube') {
            steps {
                script {
                    echo "Running SonarQube analysis..."
                    withSonarQubeEnv("${SONARQUBE_SERVER}") {
                        sh """
                            ${MAVEN_HOME}/bin/mvn sonar:sonar \
                            -Dsonar.projectKey=HelloWorld \
                            -Dsonar.projectName=HelloWorld \
                            -Dsonar.projectVersion=1.0 \
                            -Dsonar.java.binaries=target/classes \
                            -Dsonar.junit.reportPath=target/surefire-reports \
                            -Dsonar.jacoco.reportPath=target/jacoco.exec
                        """
                    }
                }
            }
        }

        stage('Quality Gate') {
            steps {
                script {
                    echo "Waiting for the SonarQube quality gate result..."
                    timeout(time: 2, unit: 'MINUTES') {
                        def qg = waitForQualityGate()
                        if (qg.status != 'OK') {
                            error "Pipeline aborted due to Quality Gate failure: ${qg.status}"
                        }
                    }
                }
            }
        }

        stage('Code Coverage - JaCoCo') {
            steps {
                echo "Collecting and publishing JaCoCo coverage report..."
                // Publish JUnit test results
                junit 'target/surefire-reports/*.xml'
                // Publish JaCoCo coverage results (requires Jenkins JaCoCo plugin)
                jacoco execPattern: 'target/jacoco.exec',
                       classPattern: 'target/classes',
                       sourcePattern: 'src/main/java'
            }
        }

        stage('Cyclomatic Complexity - Lizard') {
            steps {
                echo "Calculating cyclomatic complexity with Lizard..."
                sh """
                    # Install lizard if not present (e.g., on Debian/Ubuntu)
                    # apt-get update && apt-get install -y python3-pip
                    # pip3 install lizard

                    lizard src/main/java > lizard_report.txt
                """
                archiveArtifacts artifacts: 'lizard_report.txt'
            }
        }

        stage('Security Scan - OWASP Dependency-Check') {
            steps {
                echo "Running OWASP Dependency-Check..."
                sh """
                    # Option 1: If installed locally:
                    # dependency-check.sh --scan . --format "HTML" --project "HelloWorld"

                    # Option 2: Using Docker:
                    # docker run --rm -v \$(pwd):/src -w /src owasp/dependency-check \
                    #    --scan . --format ALL --project "HelloWorld"

                    # For demonstration, let's assume local installation:
                    dependency-check.sh --scan . --format "HTML" --project "HelloWorld"
                """
                archiveArtifacts artifacts: 'dependency-check-report.*'
            }
        }
    }

    post {
        always {
            echo "Archiving JUnit test reports..."
            junit 'target/surefire-reports/*.xml'
        }
        success {
            echo "Build succeeded. Sending success notification..."
            emailext(
                subject: "Jenkins Build SUCCESS: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "The Jenkins build succeeded. Please see the attached logs or Jenkins for details.",
                to: "team@example.com"
            )
        }
        failure {
            echo "Build failed. Sending failure notification..."
            emailext(
                subject: "Jenkins Build FAILURE: ${env.JOB_NAME} #${env.BUILD_NUMBER}",
                body: "The Jenkins build failed. Please investigate in Jenkins.",
                to: "team@example.com"
            )
        }
    }
}
