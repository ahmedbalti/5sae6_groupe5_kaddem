pipeline {
    agent any
    environment {
        NEXUS_VERSION = "nexus3"
        NEXUS_PROTOCOL = "http"
        NEXUS_URL = "192.168.56.103:8081"
        NEXUS_REPOSITORY = "maven-snapshots"
        NEXUS_CREDENTIAL_ID = "deploymentRepo"
    }
    tools {
        maven 'M2_HOME'
    }
    stages {
        stage('Checkout git') {
            steps {
               git branch: 'NihedAttia-5sae6-G5', url: 'https://github.com/ahmedbalti/5sae6_groupe5_kaddem.git'
            }
        }

        stage ('Build & JUnit Test') {
            steps {
               sh 'mvn install'
            }
            post {
              success {
                    junit 'target/surefire-reports/**/*.xml'
               }
            }
       }

        stage('SonarQube Analysis'){
            steps{
                   withSonarQubeEnv(installationName: 'sonarqube') {
                        sh ' mvn clean verify sonar:sonar \
                        -Dsonar.projectKey=KaddemnihedKey \
                        -Dsonar.projectName=Kaddemnihed \
                        -Dsonar.host.url=http://192.168.56.103:9000 \
                        -Dsonar.token=sqp_cd5faa47f1b8d76b996e0b0aea03c776926d971c'
                    }
            }
        }

        stage("Maven Build") {
            steps {
                script {
                    sh "mvn package -DskipTests=true"
                }
            }
        }

        stage("publish to nexus") {
            steps {
                script {
                    artifactPath = "target/kaddem-0.0.1-SNAPSHOT.jar";

                    echo "*** File: ${artifactPath}, group: tn.esprit, packaging: jar, version 0.0.1-SNAPSHOT ***";

                    nexusArtifactUploader(
                            nexusVersion: NEXUS_VERSION,
                            protocol: NEXUS_PROTOCOL,
                            nexusUrl: NEXUS_URL,
                            groupId: 'tn.esprit',
                            version: '0.0.1-SNAPSHOT',
                            repository: NEXUS_REPOSITORY,
                            credentialsId: NEXUS_CREDENTIAL_ID,
                            artifacts: [
                                    // Artifact generated such as .jar, .ear and .war files.
                                    [artifactId: 'kaddem',
                                     classifier: '',
                                     file      : artifactPath,
                                     type      : 'jar']
                            ]
                    );

                }
            }
        }


        stage('Build docker image'){
            steps{
                script{
                     withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                     sh 'docker login -u niheda -p ${dockerhubpwd} '
                     sh 'docker build -t niheda/kaddem:1.0 .'
                }
            }
        }
    }

        stage('Push image to Hub'){
            steps{
                script{

                   withCredentials([string(credentialsId: 'dockerhub-pwd', variable: 'dockerhubpwd')]) {
                   sh 'docker login -u niheda -p ${dockerhubpwd}'
                   sh 'docker push niheda/kaddem:1.0 '
                }
            }
        }
    }

        stage('DOCKER Compose') {
           steps {
             echo 'docker compose stage';
             sh 'docker compose up -d'
      }
    }

    }

}