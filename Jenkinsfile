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
	    	jacoco(
		    execPattern: 'target/**/*.exec',
		    classPattern: 'target/classes',
		    sourcePattern: 'src/main.java',
		    exclusionPattern: 'src/test*',
		    skipCopyOfSrcFiles: false,
		    changeBuildStatus: true,
		    minimumInstructionCoverage: '0', maximumInstructionCoverage: '100',
		    minimumLineCoverage: '0', maximumLineCoverage: '100',
		    minimumComplexityCoverage: '0', maximumComplexityCoverage: '100',
		    minimumMethodCoverage: '0', maximumMethodCoverage: '100',
		    minimumClassCoverage: '0', maximumClassCoverage: '100',
		    minimumBranchCoverage: '0', maximumBranchCoverage: '100',
		    buildOverBuild: true,
		    deltaInstructionCoverage: '100',  deltaLineCoverage: '100',
		    deltaMethodCoverage: '100',  deltaClassCoverage: '100',
		    deltaComplexityCoverage: '100',  deltaBranchCoverage: '100'
		)
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