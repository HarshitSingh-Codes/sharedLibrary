package org.opstree.launchTemplate

def terraformInit(String codePath) {
    stage('packer init') {
        script {
            sh "cd launch-template/ && terraform init "
        }
    }
}

def terraformPlan(String codePath) {
    stage('packer build') {
        script {
            sh "cd launch-template/ && terraform plan"
        }
    }
}

def terraformApply(String codePath) {
    stage('packer build') {
        script {
            sh "cd launch-template/ && terraform apply  -auto-approve"
        }
    }
}

def terraformDestroy(String codePath) {
    stage('packer build') {
        script {
            sh "cd launch-template/ && terraform destroy  -auto-approve"
        }
    }
}
