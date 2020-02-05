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
	 /*stage('junit'){
            steps {
		bat "mvn test"
            }
         }
	 stage('jacoco'){
            steps{
	    	bat "mvn clean install"
	    	jacoco()
	    }
	 }*/
	 stage('Code Analysis'){
	 	steps{
		withSonarQubeEnv('sonarqube')
			bat  'mvn clean verify sonar:sonar ¨CDsonar.login=${SONAR_AUTH_TOKEN} -Dmaven.test.skip=true'
		}
	 }
	 stage('Quality Gate'){
	 	steps{
		    timeout(time:1,unit:'HOURS'){
		         waitForQualityGate abortPipeline:true
		    }
		}
	 }
    }
    /*post{
        always{
	    junit testResults: "**/target/surefire-reports/*.xml"
	    pmd(canRunOnFailed:true,pattern:'**/target/pmd.xml')
        }
    }*/
}