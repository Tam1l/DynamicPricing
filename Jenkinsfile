pipeline {
    agent any

    tools {
        // You must have Maven configured in Jenkins under Manage Jenkins -> Global Tool Configuration
        // 'maven-3' should match the name of the Maven installation in Jenkins.
        maven 'maven-3' 
        jdk 'jdk-17' // Ensure JDK 17 is configured in Global Tool Configuration
    }

    environment {
        // Change 'yourdockerhubuser' to your actual Docker Hub username
        DOCKER_IMAGE = "yourdockerhubuser/pricing-microservice" 
        DOCKERHUB_CREDENTIALS_ID = 'docker-hub-credentials'
        KUBEKUBECONFIG_CREDENTIALS_ID = 'kube-config-credentials'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // For Windows agents, change 'sh' to 'bat'
                sh 'mvn -B clean package'
            }
            post {
                always {
                    junit 'target/surefire-reports/**/*.xml'
                }
                success {
                    archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
                }
            }
        }

        stage('Docker Build & Push') {
            when {
               branch 'main'
            }
            steps {
                script {
                    withCredentials([usernamePassword(credentialsId: env.DOCKERHUB_CREDENTIALS_ID, passwordVariable: 'DOCKER_PASS', usernameVariable: 'DOCKER_USER')]) {
                        sh "docker login -u ${DOCKER_USER} -p ${DOCKER_PASS}"
                        sh "docker build -t ${DOCKER_IMAGE}:${env.BUILD_NUMBER} -t ${DOCKER_IMAGE}:latest ."
                        sh "docker push ${DOCKER_IMAGE}:${env.BUILD_NUMBER}"
                        sh "docker push ${DOCKER_IMAGE}:latest"
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            when {
               branch 'main'
            }
            steps {
                withCredentials([file(credentialsId: env.KUBEKUBECONFIG_CREDENTIALS_ID, variable: 'KUBECONFIG')]) {
                    sh "kubectl apply -f k8s/deployment.yaml"
                    sh "kubectl apply -f k8s/service.yaml"
                }
            }
        }
    }
}
