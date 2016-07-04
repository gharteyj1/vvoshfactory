# vvoosh-webdriver-tests
The repository for all WebDriver automation tests

# Intro

These tests run on docker containers on AWS server as part of deployment pipelines in Jenkins.

Since it's hard to debug this sort of tests on the server side (and to prevent "it works on my box" scenarios) you must run these tests locally on your laptop before pushing changes to the repo.

You can first run them with a maven command for faster results and then fine tune them by pulling down the docker image and executing the tests inside a container, just like they will run on the server.

Please see the instruction below for detailed information.

# Setup

### Requirements for running tests locally with maven
* [Git lfs](https://git-lfs.github.com/)
* [Java SDK Version 8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
* [Apache Maven](http://maven.apache.org/download.cgi)
* [Google Chrome Browser](https://www.google.com/chrome/browser/desktop/)
* [Chrome Driver](https://sites.google.com/a/chromium.org/chromedriver/downloads) (ensure the driver supports your installed browser version)

### Requirements for running tests locally in Docker
* [Git lfs](https://git-lfs.github.com/)
* AWS CLI [Installed](http://docs.aws.amazon.com/cli/latest/userguide/installing.html#install-bundle-other-os) and [Configured](http://docs.aws.amazon.com/cli/latest/userguide/cli-chap-getting-started.html#cli-quick-configuration)
* [VNC Viewer](https://www.realvnc.com/download/viewer/)
* [VirtualBox (OSX only)](https://www.virtualbox.org/wiki/Downloads)
* [Docker Toolbox (OSX only)](https://www.docker.com/products/docker-toolbox)
* AWS IAM user with ecr get-login permissions (check with DevOps)

# Execution
 
### Running tests locally with maven

1. Install all of the requirements mentioned above for running tests locally with maven
1. Run the test:
	1. On a terminal:
		1. Change directory to the root of your local copy of the repository
		1. Run `mvn test -e -Denviron=uat-vpc`, replacing *uat-vpc* by the correct environment you want to run the tests against
	1. On the IDE [Intellij](https://www.jetbrains.com/idea/#chooseYourEdition):
		1. [Import a maven project into Intellij](https://www.jetbrains.com/help/idea/2016.1/importing-project-from-maven-model.html)
		1. [Run the *test* maven lifecycle](https://www.jetbrains.com/help/idea/2016.1/executing-maven-goal.html#1)  

### Running tests locally in Docker
1. Install all of the requirements mentioned above for running tests locally with docker
1. Change directory to the root of your local copy of the repository
1. Run the test:
	1. On a terminal:
		1. Change directory to the root of your local copy of the repository
		1. Run `bash local-run/run-osx.sh` 
	1. On the IDE [Intellij](https://www.jetbrains.com/idea/#chooseYourEdition):
		1. [Install](https://www.jetbrains.com/help/idea/2016.1/installing-updating-and-uninstalling-repository-plugins.html) the bash support plugin 
		1. [Create a run configuration](https://www.jetbrains.com/help/idea/2016.1/creating-and-editing-run-debug-configurations.html) for a bash script
		1. Execute the run configuration you've just created
1. While the test is running you can open VNC Viewer, and use *127.0.0.1:5900* as the server endpoint to connect to

# Building vs Deploying the docker image
All docker containers need to be based on a docker image. This image is created via a [Jenkins pipeline](https://build.vvoosh.com/job/DockerBuilds/view/Build%20Frontend%20Selenium%20Tests%20Docker%20Image/) and bundles the test code in this repository together with all of the required dependencies for them to run.

On that pipeline you see two steps, build and deploy. The Build step creates the image and tags it with a build number. You can then pull that image tag to test locally by passing the -t flag to the run-osx.sh script, like so `bash run-local/run-osx.sh -c BUILD_NUMBER`, where BUILD_NUMBER should be replaced by the Jenkins build number. If you're happy with the results you trigger a deployment by clicking on the green arrow of the deploy job which will tag the latest created image as *latest*. This means it is now live and will be the image used to run the tests after each frontend code deployments to the environments.

The Build step will use code from the develop branch of this repository which means that only code that has been reviewed and merged into develop will end up on the image. During your develop and test cycles you don't need to build new docker containers. You can pass the -c flag to the run-osx.sh script to make it copy your code into the container like so `bash run-local/run-osx.sh -c` and run it.

You can always run `bash run-local/run-osx.sh -h` to get a list of flags on the script.


 
