package org.opstree.launchTemplate

def terraformInit(String codePath) {
    stage('terraform init') {
        script {
            sh "cd ${codePath} && terraform init "
        }
    }
}

def terraformPlan(String codePath) {
    stage('terraform  plan') {
        script {
            sh "cd ${codePath} && terraform plan"
        }
    }
}

def terraformApply(String codePath) {
    stage('terraform apply') {
        script {
            sh "cd launch-template/ && terraform apply  -auto-approve"
        }
    }
}

def terraformDestroy(String codePath) {
    stage('terraform destroy') {
        script {
            sh "cd launch-template/ && terraform destroy  -auto-approve"
        }
    }
}
