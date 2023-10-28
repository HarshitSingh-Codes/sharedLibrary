def call() {
    node {
    stage('Checkout') {
        checkout scmGit(branches: [[name: '*/main']], extensions: [], userRemoteConfigs: [[url: 'https://github.com/HarshitSingh-Codes/sharedLibrary.git']])
        }
    }
}

