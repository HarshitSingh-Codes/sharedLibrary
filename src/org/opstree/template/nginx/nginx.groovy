package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def call(String url, String creds, String branch, boolean runPacker, String packerFileName){
    
    def gitCheckout = new gitCheckout()
    def packer = new runPacker()
    gitCheckout.call(url, creds, branch)

    if (runPacker) {
            packer.packerInit()
            packer.packerBuild(packerFileName)
    } 
    // else if (action == 'destroy') {
        //     approval.call(destroyMsg)
        //     utils.destroy(rootPath, childPath)
    // } 
    else {
        script {
            print("skipping run packer")
        }    }
}