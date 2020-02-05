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
				bat  'mvn clean verify sonar:sonar -Dsonar.login=b8e07932b560209deaae8fe4e5035e5b06301a54 -Dmaven.test.skip=true -Dsonar.host.url=http://127.0.0.1:9000 -Dmaven.test.skip=true'
			}
		}
	 }
	 stage('Quality Gate'){
	 	steps{
		    timeout(time:1,unit:'HOURS'){
		    	sleep(5)
		         waitForQualityGate abortPipeline:true
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