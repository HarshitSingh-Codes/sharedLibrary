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
            //sh "packer build ${fileName}"
            sh """packer build -force \
              -var "golden_ami_name=golden-ami" \
              -var "image_name=nginx" \
              -var "image_version=0.4" \
              -var "filePath=./index2.html" .
            
            export AMI_NAME=$(jq -r '.builds[] | select(.name == "nginx-ami") | .custom_data.ami_name' manifest.json)
            echo $AMI_NAME
            """
            
        }
    }
}
def displayAmiName() {
    stage('packer build') {
        script {
            sh "echo $AMI_NAME"
        }
    }
}
