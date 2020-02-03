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
		execPattern: 'target/**/*.exec ', //���븲����ͳ���ļ�λ��
		classPattern: 'target/classes', //class�ļ�λ��
		sourcePattern: 'src/main.java', //Դ�����ļ�λ��
		exclusionPattern: 'src/test*', //�ų������ļ�λ��
		skipCopyOfSrcFiles: false, //�Ƿ����ÿ��ÿ�и������ļ���ԭ�ļ���ʾ
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