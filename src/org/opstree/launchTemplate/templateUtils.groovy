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
            sh "cd ${codePath} && terraform -var ami_name=${amiName} plan"
        }
    }
}

def terraformApply(String codePath) {
    stage('terraform apply') {
        script {
            sh "cd ${codePath} && terraform apply -var ami_name=${amiName} -auto-approve"
        }
    }
}

def terraformDestroy(String codePath) {
    stage('terraform destroy') {
        script {
            sh "cd ${codePath} && terraform destroy  -auto-approve"
        }
    }
}
