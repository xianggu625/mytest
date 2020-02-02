pipeline {
    agent any
    
    tools{
        maven 'mvn-3.6.3'
    }
    
    stages{
        stage('pmd'){
            steps {
                bat "mvn pmd:pmd"
            }
         }
    }
    post{
        always{
            pmd(canRunOnFailed:true,pattern:'**/target/pmd.xml')
        }
    }
    post{
        always{
	    junit testResults:"**/target/surefile-report/*.xml"
        }
    }
}