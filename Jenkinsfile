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
				-Dsonar.login=0df7399ff1fd3afa7c0adfcb654fa77d8a67b836 \
				-Dsonar.login=admin \
				-Dsonar.password=admin \
				-Dsonar.host.url=http://127.0.0.1:9000
				'''
			}
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
    post{
        always{
	    junit testResults: "**/target/surefire-reports/*.xml"
	    pmd(canRunOnFailed:true,pattern:'**/target/pmd.xml')
        }
    }
}


// No need to occupy a node
stage("Quality Gate"){
  timeout(time: 1, unit: 'HOURS') { // Just in case something goes wrong, pipeline will be killed after a timeout
    def qg = waitForQualityGate() // Reuse taskId previously collected by withSonarQubeEnv
    if (qg.status != 'OK') {
      error "Pipeline aborted due to quality gate failure: ${qg.status}"
    }
  }
}