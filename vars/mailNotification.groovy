    post {
        always {
            emailext body: '$DEFAULT_CONTENT ', subject: 'Assignment -06 Status', to: 'harshitpanu7900@gmail.com'
            echo 'Email sent'
        }
    }
