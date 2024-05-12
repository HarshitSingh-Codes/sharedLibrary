package org.opstree.packer

def packerInit() {
    stage('packer init') {
        script {
            sh "packer init ."
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