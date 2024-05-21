package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def call(Map step_params){
    
    def gitCheckout = new gitCheckout()
    def packer = new runPacker()
    def updateLaunchTemplate =  new awsCliUtils()
    def instanceRefresh =  new awsCliUtils()

    if (step_params.gitCheckout == true) {
        url = "${step_params.url}"
        creds = "${step_params.creds}"
        branch = "${step_params.branch}"
        gitCheckout.call(url, creds, branch)
    }

    if (step_params.runPacker == true) {
        runPacker = "${step_params.runPacker}"
        goldenAmiName = "${step_params.goldenAmiName}"
        amiName = "${step_params.amiName}"
        amiVersion = "${step_params.amiVersion}"
        rootFolderName = "${step_params.rootFolderName}"
        packerFileName = "${step_params.packerFileName}"

        fileName = "${step_params.rootFolderName}/${step_params.packerFileName}"
        
        packer.packerInit(fileName)
        packer.packerBuild(goldenAmiName, amiName, amiVersion, fileName)
    }
}
// def runPacker(String packerFileName, String amiVersion){
    
//     def packer = new runPacker()
    
//     packer.packerInit(packerFileName)
//     packer.packerBuild(packerFileName, amiVersion)
// }

// def updateLaunchTemplate(String templateID, String sourceVersion, versionDescription){
    
//     def update =  new awsCliUtils()
    
//     def amiID = sh(
//         script: '''
//         jq -r '.builds[].artifact_id' manifest.json | cut -d ":" -f2
//         ''',
//         returnStdout: true
//         ).trim()    
    
//     update.updateLaunchTemplate(templateID, amiID, sourceVersion, versionDescription)
// }

// def instanceRefresh(String asgConfig) {
    
//     def instanceRefresh =  new awsCliUtils()

//     instanceRefresh.startInstanceRefresh(asgConfig)
// }
