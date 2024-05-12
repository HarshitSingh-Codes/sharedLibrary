package org.opstree.common

def call(String url, String creds, String branch) {
    stage('GIT Checkout') {
        script {
            git branch: "${branch}", credentialsId: "${creds}", url: "${url}"
        }
    }
}