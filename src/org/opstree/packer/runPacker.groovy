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
            // script {
            //     def amiName = sh(
            //         script: 'jq -r \'.builds[] | select(.name == "nginx-ami") | .custom_data.ami_name\' manifest.json',
            //         returnStdout: true
            //     ).trim()
            //     env.AMI_NAME = amiName
            // }
        }   
}
def displayAmiName() {
    stage('AMI Name') {
            script {
                def amiName = sh(
                    script: 'jq -r \'.builds[] | select(.name == "nginx-ami") | .custom_data.ami_name\' manifest.json',
                    returnStdout: true
                ).trim()
                
                def amiID = sh(
                    script: '''
                    jq -r '.builds[].artifact_id' manifest.json | cut -d ":" -f2
                    ''',
                    returnStdout: true
                ).trim()
                env.AMI_NAME = amiName
                env.AMI_AD = amiID
            }
        sh "echo 'Name--> ${AMI_NAME} ID--> ${AMI_ID} is created.'"
    }
}
