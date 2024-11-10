def jobName = ['NGINX-1','NGINX-2','NGINX-3']

for (name in jobName) {
    pipelineJob(name) {
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
}
