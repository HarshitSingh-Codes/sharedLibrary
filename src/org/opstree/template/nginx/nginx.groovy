package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def call(String url, String creds, String branch, boolean runPacker, String packerFileName, boolean runTerraform, String terraformDirPath){
    
    def gitCheckout = new gitCheckout()
    def packer = new runPacker()
    def terraform = new templateUtils()
    gitCheckout.call(url, creds, branch)

    if (runPacker) {
            packer.packerInit(packerFileName)
            packer.packerBuild(packerFileName)
    } 
    // else if (action == 'destroy') {
        //     approval.call(destroyMsg)
        //     utils.destroy(rootPath, childPath)
    // } 
    else {
        script {
            print("skipping run packer")
        }    
    }

    if (runTerraform) {
        terraform.terraformInit(terraformDirPath)
        terraform.terraformPlan(terraformDirPath)
    } 
    // else if (action == 'destroy') {
        //     approval.call(destroyMsg)
        //     utils.destroy(rootPath, childPath)
    // } 
    else {
        script {
            print("skipping run terraform")
        }    
    }
}
