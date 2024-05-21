package org.opstree.packer

def packerInit(String fileName) {
    stage('packer init') {
        script {
            sh "packer init ${fileName}"
        }
    }
}
def packerBuild(String goldenAmiName, String amiName, String amiVersion, String fileName) {
    stage('packer build') {
            //sh "packer build ${fileName}"
            sh """
            packer build -force \
            -var 'golden_ami_name=${goldenAmiName}' \
            -var 'image_name=${amiName}' \
            -var 'image_version=${amiVersion}' \
            -var 'filePath=./index2.html' ${fileName}
            """
        }   
}