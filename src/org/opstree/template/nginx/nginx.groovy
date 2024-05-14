package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def checkout(String url, String creds, String branch){
    
    def gitCheckout = new gitCheckout()
    
    gitCheckout.call(url, creds, branch)
}

def runPacker(String packerFileName, String amiVersion){
    
    def packer = new runPacker()
    
    packer.packerInit(packerFileName)
    packer.packerBuild(packerFileName, amiVersion)
}

def updateLaunchTemplate(String templateID){
    
    def packer = new runPacker()
    def update =  new awsCliUtils()
    
    def  ami_id = packer.displayAmiName()
    
    packer.displayAmiName()

}
