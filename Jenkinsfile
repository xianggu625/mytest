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
	 stage('junit'){
            steps {
		bat "mvn test"
            }
         }
    }
    post{
        always{
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
