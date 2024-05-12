package org.opstree.packer

def packerInit(String fileName) {
    stage('packer init') {
        script {
            sh "packer init ${fileName}"
        }
    }
}
def packerBuild(String fileName) {
    stage('packer build') {
        script {
            sh "packer build ${fileName}"
        }
    }
}