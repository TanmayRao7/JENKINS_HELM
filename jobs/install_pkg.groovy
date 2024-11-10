pipelineJob('Install Argocd')  {
    agent any

    environment {
        ARGOCD_VERSION = 'v2.6.5'   
        ARGOCD_CLI_BIN = '/usr/local/bin/argocd'
    }

    stages {
        stage('Install Argo CD CLI') {
            steps {
                script {
                    echo "Installing Argo CD CLI version ${ARGOCD_VERSION}..."
                    sh '''
                    curl -sSL -o /tmp/argocd.tar.gz https://github.com/argoproj/argo-cd/releases/download/${ARGOCD_VERSION}/argocd-linux-amd64
                    mv /tmp/argocd-linux-amd64 ${ARGOCD_CLI_BIN}
                    chmod +x ${ARGOCD_CLI_BIN}
                    '''
                }
            }
        }

        stage('Verify Installation') {
            steps {
                script {
                    echo "Verifying Argo CD CLI installation..."
                    sh "${ARGOCD_CLI_BIN} version"
                }
            }
        }

        stage('Login to Argo CD') {
            steps {
                script {
                    echo "Logging in to Argo CD..."
                    sh '''
                    ${ARGOCD_CLI_BIN} login <ARGOCD_SERVER> --username <USERNAME> --password <PASSWORD> --insecure
                    '''
                }
            }
        }

        stage('Sync Argo CD Application') {
            steps {
                script {
                    echo "Syncing Argo CD application..."
                    sh '''
                    ${ARGOCD_CLI_BIN} app sync <APPLICATION_NAME>
                    '''
                }
            }
        }
    }

    post {
        always {
            echo 'Cleaning up...'
        }
    }
}
