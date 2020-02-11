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
	    script{
	       allure([
	         includeProperties:false,
	         jdk:'',
	         properties: [],
		 reportBuildPolicy:'ALWAYS',
		 results: [[path: 'target/allure-results']]
	      ])
	   }
        }
    }
}

