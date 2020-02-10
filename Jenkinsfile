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
	 stage('jacoco'){
            steps{
	    	jacoco()
	    }
	 }
	 stage('Code Analysis'){
	 	steps{
		withSonarQubeEnv('sonarqube'){
				bat  '''
				mvn clean verify sonar:sonar \
				-Dsonar.login=6cdf6f3176211d67dbf469712168c005c48f8c4d \
				-Dsonar.login=admin \
				-Dsonar.password=admin \
				-Dsonar.host.url=http://127.0.0.1:9000
				'''
			}
		}
	 }
	 stage('Quality Gate'){
	 	steps{
			script {
				timeout(time:1,unit:'HOURS'){
		    		sleep(5)
				def qg = waitForQualityGate()
				if (qg.status != 'OK') {
					echo "Status: ${qg.status}"
					error "Pipeline aborted due to quality gate failure: ${qg.status}"
				}
			}
		     }
	      }
	 }
    }
    post{
        always{
	    pmd(canRunOnFailed:true,pattern:'**/target/pmd.xml')
	    script{
	       allure([
	         includeProperties:false,
	         jdk:'',
	         properties: [],
	         reportBuildPolicy:"ALWAYS",
		 results: [[path: 'target/allure-results']]
	      ])
	   }
        }
    }
}
