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
	 stage('Code Analysis'){
	 	steps{
		withSonarQubeEnv('sonarqube'){
				bat  'mvn clean verify sonar:sonar ¨CDsonar.login=b8e07932b560209deaae8fe4e5035e5b06301a54 -Dmaven.test.skip=true'
			}
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
}