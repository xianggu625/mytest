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
		execPattern: 'target/**/*.exec ', //代码覆盖率统计文件位置
		classPattern: 'target/classes', //class文件位置
		sourcePattern: 'src/main.java', //源代码文件位置
		exclusionPattern: 'src/test*', //排除分析文件位置
		skipCopyOfSrcFiles: false, //是否禁用每行每行覆盖率文件的原文件显示
		changeBuildStatus: true 
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