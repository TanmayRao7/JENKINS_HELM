import hudson.plugins.git.*;

def scm = new GitSCM("git@github.com:TanmayRao7/JENKINS_HELM.git")
scm.branches = [new BranchSpec("*/main")];

def flowDefinition = new org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition(scm, "Jenkinsfile")

def parent = Jenkins.instance
def job = new org.jenkinsci.plugins.workflow.job.WorkflowJob(parent, "NGINX")
job.definition = flowDefinition

parent.reload()