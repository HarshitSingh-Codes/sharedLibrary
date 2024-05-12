package org.opstree.template

import org.opstree.common.*
import org.opstree.launchTemplate.*
import org.opstree.packer.*

def call(String url, String creds, String branch, String zapVersion, String currentWorkspace){
    
    def gitCheckout = new gitCheckout()

    gitCheckout.call(url, creds, branch)
    
}