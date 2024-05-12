package org.opstree.common

def call(String msg) {
    stage('Approval') {
        input message: "${msg}", ok: 'Yes'
    }
}
