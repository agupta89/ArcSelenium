pipeline{
		agent any
	
	parameters{
		choice(choices: ['master', 'stg', 'dev', 'qas'], description: 'Select a branch to build project', name: 'environment')
                choice(choices: ['firefox', 'chrome', 'ie'], description: 'Select browser to build project', name: 'browserName')
		
	}
	        
	         stages{
		
			stage('Building Project') 
		{
		steps{
		echo "Building the C.."
			echo "Building th."
			echo "Building th."
			
		git branch: "${params.environment}", url: 'https://github.com/saurav1501/ArcSelenium.git'
		echo "Building the Code........."
		echo "Building the Code........."

		shell "mvn clean install"
		}
		}
		
		stage('Excuting Code...') 
		 {
		steps{
		  wrap([$class: 'Xvfb', additionalOptions: '', assignedLabels: '', autoDisplayName: true, debug: false, shutdownWithBuild: true ,displayNameOffset: 1,installationName: 'Xvfb', parallelBuild: true, screen: '1600x1280x24', timeout: 60])
		  {
		  sh "mvn -f pom.xml clean install"
	   	  }
		  }
		}
		
	stage('Publish Html Report') {
            steps {
                echo 'Extend Report'    
        
        }
      }
     }
	   
      post {
        always {
         publishHTML([allowMissing: true, alwaysLinkToLastBuild: true, keepAll: true, reportDir: 'Reports', reportFiles: 'ARC_UITestingReport_Building.html', reportName: 'ExtendReport', reportTitles: ''])
	     emailext (to: 'ssinha@usgbc.org', subject: "Email Report subject: '${env.JOB_NAME} - BuildNumber # ${env.BUILD_NUMBER}' ", 
         body : readFile("src/test/java/com/arc/utility/custom-emailable-report.html"),   
         mimeType: 'text/html');
	}
   }
      
}
	
