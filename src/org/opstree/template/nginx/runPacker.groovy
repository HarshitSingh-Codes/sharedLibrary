package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def call(String url, String creds, String branch, String packerFileName, String amiVersion){
    
    def gitCheckout = new gitCheckout()
    def packer = new runPacker()
    
    gitCheckout.call(url, creds, branch)

    packer.packerInit(packerFileName)
    packer.packerBuild(packerFileName, amiVersion)
    packer.displayAmiName()

}
