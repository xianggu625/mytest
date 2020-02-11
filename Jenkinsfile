pipeline {
    agent any
    
    tools{
        maven 'mvn-3.6.3'
    }
    
    stages{
	 stage('junit'){
            steps {
		bat "mvn test"
            }
         }
    }
    post{
        always{
	    junit testResults: "**/target/surefire-reports/*.xml"
	    pmd(canRunOnFailed:true,pattern:'**/target/pmd.xml')
	    script{
	       allure([
	         includeProperties:false,
	         jdk:'',
	         properties: [],
		 results: [[path: 'target/allure-results']]
	      ])
	   }
        }
    }
}

