pipeline {
    agent any
    tools {
        maven "maven3"
        jdk "JAVA_HOME"
    }

    environment {
        // This can be nexus3 or nexus2
        NEXUS_VERSION = "nexus3"
        // This can be http or https
        NEXUS_PROTOCOL = "http"
        // Where your Nexus is running
        NEXUS_URL = "192.168.33.10:8081"
        // Repository where we will upload the artifact
        NEXUS_REPOSITORY = "5sae6_groupe5_kaddem"
        // Jenkins credential id to authenticate to Nexus OSS
        NEXUS_CREDENTIAL_ID = "nexusCredential"

    }

    stages {
        stage("Check out") {
            steps {
                script {
                    git branch: 'IkramBargaoui-5SAE6-G5-Kaddem', url: 'https://github.com/ahmedbalti/5sae6_groupe5_kaddem.git';
                }
            }
        }

        stage("mvn build") {
            steps {
                script {
                    sh "mvn clean package"
                }
            }
        }

        stage("publish to nexus") {
            steps {
                script {
                  withCredentials([usernamePassword(credentialsId: 'nexusCredential', usernameVariable: 'admin', passwordVariable: 'nexus')]) {
                                          // Use NEXUS_USERNAME and NEXUS_PASSWORD to authenticate and publish artifacts
                 sh "mvn deploy:deploy-file -Durl=http://192.168.33.10:8081/repository/5sae6_groupe5_kaddem -DrepositoryId=nexus-repo -DgroupId=tn.esprit.spring -DartifactId=kaddem -Dversion=1 -Dpackaging=jar -Dfile=target/kaddem-0.0.1-SNAPSHOT.jar -DgeneratePom=false"



                    }
                }
            }
        }

    }
}
