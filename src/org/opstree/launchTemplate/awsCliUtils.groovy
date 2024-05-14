package org.opstree.launchTemplate

def updateLaunchTemplate(String templateID, String amiID) {
    stage('Update launch template') {
            sh '''
            aws ec2 create-launch-template-version \
            --launch-template-id ${templateID} \
            --version-description WebVersion  \
            --launch-template-data '{ \
            "ImageId":"${amiID}" \
            }' 
            '''
    }
}
