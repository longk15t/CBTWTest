## CBTW QA Coding Challenge
This is an automation framework for CBTW QA Coding challenge including Web application and Mobile application automation tests

### Setup
- Java JDK 17
- Maven 3.9.0

### Usage
- Configure test execution by config.json file, this file currently is used for just web application. We can integrate mobile test configuration (device, appName, ...).

### Run test
`mvn test` will run only the test case of CTFLearner (web application)

### Notes
- The mobile automation part could be not runnable due to the complex configuration/setup, but the current design applies for both platforms Android and iOS with just one source.
- There are some classes can be grouped together between both projects for reusability (utilities classes, listener classes, ...)
- The sample Jenkinsfile is used for integration with Jenkins system
- Current reporting library is surefire-report in target folder. We can integrate with other popular reporting libraries such as Extent Reports (latest version supports Gherkin description) or Allure report

