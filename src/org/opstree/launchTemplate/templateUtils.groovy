package org.opstree.launchTemplate

def terraformInit(String codePath) {
    stage('terraform init') {
        script {
            sh "cd ${codePath} && terraform init "
        }
    }
}

def terraformPlan(String codePath, String amiName) {
    stage('terraform  plan') {
        script {
            sh "cd ${codePath} && terraform  plan -var ami_name=${amiName}"
        }
    }
}

def terraformApply(String codePath, String amiName) {
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
