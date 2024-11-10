def jobName = ['NGINX-1']

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
        parameters {
            choiceParam('ENV', ['dev', 'test', 'stage', 'prod'], 'Choose the deployment environment')
            stringParam('IMAGE_TAG', '', 'Specify the image tag')
            stringParam('CUSTOM_TEXT', '', 'Custom text for configuration')
        }
    }
}
