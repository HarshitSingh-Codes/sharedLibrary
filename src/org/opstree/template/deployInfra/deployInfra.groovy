package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def call(Map step_params){
    
    def gitCheckout = new gitCheckout()
    def packer = new runPacker()
    def awsUtils =  new awsCliUtils()

    if (step_params.gitCheckout == true) {
        url = "${step_params.url}"
        creds = "${step_params.creds}"
        branch = "${step_params.branch}"
        gitCheckout.call(url, creds, branch)
    } else {
        println("gitCheckout step not executed because gitCheckout parameter is false or not provided.")
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
    } else {
        println("runPacker step not executed because runPacker parameter is false or not provided.")
    }


    if (step_params.updateLaunchTemplate == true) {

        templateID = "${step_params.templateID}"
        sourceVersion = "${step_params.sourceVersion}"
        versionDescription = "${step_params.versionDescription}"
        
        def amiID = sh(
        script: '''
        jq -r '.builds[].artifact_id' manifest.json | cut -d ":" -f2
        ''',
        returnStdout: true
        ).trim()   

        awsUtils.updateLaunchTemplate(templateID, amiID, sourceVersion, versionDescription)

    } else {
        println("updateLaunchTemplate step not executed because updateLaunchTemplate parameter is false or not provided.")
    }

    if (step_params.startInstanceRefresh == true) {

        // asgConfigRootdir = "${step_params.asgConfigRootdir}"
        // asgConfigFilename = "${step_params.asgConfigFilename}"

        asgConfig = "${step_params.asgConfigRootdir}/${step_params.asgConfigFilename}"
        awsUtils.startInstanceRefresh(asgConfig)

    } else {
        println("startInstanceRefresh step not executed because startInstanceRefresh parameter is false or not provided.")
    }

}