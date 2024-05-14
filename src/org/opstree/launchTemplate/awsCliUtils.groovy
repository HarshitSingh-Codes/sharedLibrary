package org.opstree.launchTemplate

def updateLaunchTemplate(String templateID, String amiID, String sourceVersion) {
    stage('Update launch template') {
            sh """
            aws ec2 create-launch-template-version \
            --launch-template-id ${templateID} \
            --source-version ${sourceVersion} \
            --version-description WebVersion  \
            --launch-template-data '{ \
            "ImageId":"${amiID}" \
            }' 
            """
    }
}
def startInstanceRefresh(String asgConfig) {
    stage('Start Instance Refresh') {
            sh """
            aws autoscaling start-instance-refresh \
            --cli-input-json file://${asgConfig}
            """
    }
}
