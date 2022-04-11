node {
  stage ('Build') {
    git url: 'https://github.com/gavura256/api-test-with-rest-assured'
    withMaven {
      sh "mvn clean verify"
    } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
  }
    stage ('Test') {
      git url: 'https://github.com/gavura256/api-test-with-rest-assured'
      withMaven {
        sh "mvn test"
      } // withMaven will discover the generated Maven artifacts, JUnit Surefire & FailSafe reports and FindBugs reports
    }
}