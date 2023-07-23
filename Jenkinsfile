pipeline {
    agent any

    environment {
        DOCKER_REGISTRY_URL = 'docker.io'
        DOCKER_USERNAME = credentials('docker-credentials-id')
        SSH_CREDENTIALS_ID = credentials('ssh-credentials-id')
        SSH_USERNAME = env.SSH_USER
        REMOTE_SERVER = env.REMOTE_SERVER_IP
        CONTAINER_NAME = env.CONTAINER_NAME
        DOCKER_IMAGE_NAME = 'openjdk:8-jre-slim'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout([$class: 'GitSCM', branches: [[name: '*/main']], userRemoteConfigs: [[url: 'https://github.com/hadran9/gestionFinanciere']]])
            }
        }
        
        stage('Build and Test') {
            steps {
                container('maven:3-jdk-8') {
                    sh 'mvn clean package'
                    sh 'mvn test'
                }
            }
        }

        stage('Docker Build and Push') {
            steps {
                container('docker') {
                    sh 'docker build -t $DOCKER_REGISTRY_URL/$DOCKER_IMAGE_NAME:latest .'
                    
                    sh 'docker login -u $DOCKER_USERNAME -p $DOCKER_PASSWORD $DOCKER_REGISTRY_URL'
                    
                    sh 'docker push $DOCKER_REGISTRY_URL/$DOCKER_IMAGE_NAME:latest'
                }
            }
        }

        stage('Deployment') {
            steps {
                withCredentials([sshUserPrivateKey(credentialsId: SSH_CREDENTIALS_ID, keyFileVariable: 'SSH_KEY')]) {
                    sh "ssh -i $SSH_KEY $SSH_USERNAME@$REMOTE_SERVER 'docker stop $CONTAINER_NAME || true && docker rm $CONTAINER_NAME || true && docker pull $DOCKER_REGISTRY_URL/$DOCKER_IMAGE_NAME:latest && docker run -d --name $CONTAINER_NAME -p 8181:8181 $DOCKER_REGISTRY_URL/$DOCKER_IMAGE_NAME:latest'"
                }
            }
        }
    }

    post {
        always {
            sh 'docker system prune -af'
        }
    }
}
