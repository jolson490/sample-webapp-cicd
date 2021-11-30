# Sample Java webapp with CI/CD to AWS

## Summary

This is a (relatively simple) Java EE web application, built with the Spring platform (e.g. Spring Boot and Spring MVC), which allows a pair of temperature or volume values and units to be specified and this app will indicate whether the two values are equal to each other once each is rounded to the nearest tenth.

## Deployed in AWS

There's an instance of this application running at: [http://jolson490-sampleapp.us-east-2.elasticbeanstalk.com/](http://jolson490-sampleapp.us-east-2.elasticbeanstalk.com/) (please feel free to play around with it and check it out!)

## CI/CD

Via AWS CodePipeline, the following is setup:
 * A GitHub webhook triggers the pipeline whenever a commit is pushed to the master branch of this repo - at which point a build in AWS CodeBuild is started. Automated tests are run as part of the build, and if any of the tests fail then the build will fail too.
 * If/when a build finishes successfully, then the pipeline deploys the build artifact to an environment in AWS Elastic Beanstalk (which is available at the aforementioned URL).

## Your Homework

Hey Flexion engineers, now it's my turn to give you some work! I challenge you to do the following:
 * Go to the home page of this app in AWS and take note of the date/time that is displayed.
 * Push a commit to the master branch of this repo. Any commit that doesn't break the app will do - e.g. feel free to add something fun to the home page of the app.
 * Within ~2 minutes, on the home page you'll see an updated date/time - which shows that the pipeline did its thing.

## Prioritized To-do List

Here's the list (in priority order - highest priority items listed first) of development tasks I would do next (if I were getting paid to work on this app <img src="https://www.netclipart.com/pp/m/176-1768758_clipart-transparent-download-wink-emoticon-smiley-face-tongue.png" alt="winking smiley face" height="25" width="25"> ) to improve this solution to the code challenge. Assuming this is an app that is used by clients and has an SLA such that we/Flexion making $ is dependent on the app staying up ~99% of the time:
 * Here's the MVP to be done prior to this app being made publicly available in a production environment:
    * Add more [automated tests](src/test/java/us/flexion/sampleapp/units/) to cover the rest of the app besides the `determineOutput` method in `UnitsController`.
    * Create an error page - so that trying to access a page that doesn't exist (e.g. [http://jolson490-sampleapp.us-east-2.elasticbeanstalk.com/xyz](http://jolson490-sampleapp.us-east-2.elasticbeanstalk.com/xyz)) doesn't yield the generic "Whitelabel Error Page".
    * Create both a non-production/dev and production instance of this app. Change the production URL from `http://jolson490-sampleapp.us-east-2.elasticbeanstalk.com/sampleapp/` to `https://sampleapp.flexion.us`. Setup dev as `https://dev.sampleapp.flexion.us`. Continue to have the pipeline for dev. Determine whether there should be a manual approval needed before a pipeline deploys a new commit/release to production. For production the last updated/deployed date/time should be a hidden html field - not displayed on the rendered html. And setup monitoring to notify the developer team supporting this app at least 1 month in advance of when the SSL certificate is going to expire.
    * Setup New Relic to monitor the app - such that if the app is down then send a notification to the team: via Slack for dev or production, and via PagerDuty for production.
    * It might be ideal to change the primary appender in [log4j2-prod.xml](src/main/resources/log4j2-prod.xml) to write the logs to an external location, such as Splunk, if it ends up being inconvenient/insufficient to access the logs via AWS Elastic Beanstalk.
    * Given the anticipated usage of the app, consider whether we need to do any of the following: make the app more [secure](https://spring.io/guides/gs/securing-web/) (e.g. add authentication), specify any JVM arguments (e.g. for heap size), and/or setup the Auto Scaling group in the Elastic Beanstalk environment to do load balancing (to handle increased usage of the app).

And here's the list of non-development tasks (i.e. probably no code changes involved) I'd do prior to this app going live in production:
 * Ask Flexion to pay for me to upgrade to GitHub Pro <img src="https://images.emojiterra.com/google/android-nougat/512px/1f601.png" alt="beaming smiley face" height="25" width="25"> so that it could enabled for PRs to be required for commits to the master branch of this private repo. And require that the automated tests must pass in order for a PR to be merged.
 * Have this app reviewed by the security and legal departments in Flexion - e.g. ask legal if Flexion needs to pay Oracle for a commercial license in order to legally use Oracle JDK in production (perhaps unless we're using OpenJDK).

And some post-MVP development tasks:
 * Codify the pipeline, which was created manually via the AWS console.
 * Consider if we need other types of tests besides `org.junit.Test` - e.g. Selenium. Verify the app works on each browser/version that the app is required to.
 * Generate javadocs.
 * Make the UI look nicer - add CSS (e.g. a splash of color).
 * Utilize AJAX so the user can click the `Check Answer` button multiple times without the page being re-loaded each time.
 * Ask the other developers maintaining this app if we'd all like to have the same formatting applied automatically by our IDE whenever we save a file, so all the code is formatted the same way - for consistency/readability. e.g. if we're all using Eclipse then check in the `.settings/` directory to GitHub.
 * Consider containerizing the app (probably with Docker) and migrating it from Elastic Beanstalk to Kubernetes (perhaps Amazon EKS). 

## Developer Localhost Instructions

This section contains info for any developer interested in setting up & experimenting with this application on your own machine (e.g. to make code changes).

[Eclipse](https://eclipse.org/ide/) of course isn't the only way to run this application, but these instructions assume you're using Eclipse (personally I use "Eclipse IDE for Enterprise Java Developers").

In addition to Eclipse, you need the following installed on your machine: 
* JDK version 8
* The [Lombok Project](https://projectlombok.org/setup/eclipse)
* [Spring Tools 4](https://marketplace.eclipse.org/content/spring-tools-4-spring-boot-aka-spring-tool-suite-4)

Setup in Eclipse:
* Do `git clone https://github.com/jolson490/sample-webapp-cicd.git` in your Eclipse workspace folder.
* Import, Maven, Existing Maven Projects, then choose the `sample-webapp-cicd` folder you just obtained via `git clone`.
* Right-click this `sample-webapp-cicd` project in Eclipse, Run As, Maven install.
* If needed to resolve any errors in Eclipse: right-click the project, Maven, Update Project.

To start the application in Eclipse: click the project, `Alt+Shift+X,B` to Run Spring Boot App. Then you can access the application via your browser at: [http://localhost:8080/sampleapp/](http://localhost:8080/sampleapp/)
