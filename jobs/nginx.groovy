pipelineJob('example-scm-job') {
    description('Pipeline job configured using Job DSL and SCM')
    definition {
        cpsScm {
            scm {
                git {
                    remote {
                        url('https://github.com/TanmayRao7/nginx-helm.git') 
                    }
                    branch('main') 
                }
            }
            scriptPath('Jenkinsfile')
        }
    }
}
