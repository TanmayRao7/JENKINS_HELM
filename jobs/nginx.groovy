pipelineJob('NGINX') {
    description('Pipeline for nginx helm chart')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/TanmayRao7/nginx-helm.git') 
                    }
                    branch('master') 
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}
