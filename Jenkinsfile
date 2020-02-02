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
	    	bat "mvn clean install"
		jacoco(
		    execPattern: 'target/**/*.exec',
		    classPattern: 'target/classes',
		    sourcePattern: 'src/main.java',
		    exclusionPattern: 'src/test*',
		    skipCopyOfSrcFiles: false,
		    changeBuildStatus: true,
		    minimumInstructionCoverage: '30', maximumInstructionCoverage: '70',
		    minimumLineCoverage: '30', maximumLineCoverage: '70',
		    minimumComplexityCoverage: '30', maximumComplexityCoverage: '70',
		    minimumMethodCoverage: '30', maximumMethodCoverage: '70',
		    minimumClassCoverage: '30', maximumClassCoverage: '70',
		    minimumBranchCoverage: '30', maximumBranchCoverage: '70',
		    buildOverBuild: true,
		    deltaInstructionCoverage: '80',  deltaLineCoverage: '80',
		    deltaMethodCoverage: '80',  deltaClassCoverage: '80',
		    deltaComplexityCoverage: '80',  deltaBranchCoverage: '80'
		)
	    }
	 }
    }
    post{
        always{
	    //junit testResults: "**/target/surefire-reports/*.xml"
	    //pmd(canRunOnFailed:true,pattern:'**/target/pmd.xml')
        }
    }
}