package org.opstree.packer

def packerInit(String fileName) {
    stage('packer init') {
        script {
            sh "packer init ${fileName}"
        }
    }
}
def packerBuild(String fileName, String amiVersion) {
    stage('packer build') {
            //sh "packer build ${fileName}"
            sh """
            packer build -force \
            -var 'golden_ami_name=golden-ami' \
            -var 'image_name=nginx' \
            -var 'image_version=${amiVersion}' \
            -var 'filePath=./index2.html' ${fileName}
            """
        }
}
def displayAmiName() {
    stage('store AMI Name') {
        script {
            def amiName = sh(
                script: 'jq -r \'.builds[] | select(.name == "nginx-ami") | .custom_data.ami_name\' manifest.json',
                returnStdout: true
            ).trim()
            env.AMI_NAME = amiName
        }    
        sh "echo $AMI_NAME"
    }
}
