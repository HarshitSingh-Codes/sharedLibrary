package org.opstree.launchTemplate

def updateLT(String amiID) {
    stage('Update launch template') {
        script {
            sh '''
            aws ec2 create-launch-template-version \
            --launch-template-id lt-0e0cf0cd693670427 \
            --version-description WebVersion  \
            --launch-template-data '{ \
            "ImageId":${amiID} \
            }' 
            '''
        }
    }
}
