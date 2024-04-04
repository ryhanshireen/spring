# Overview
* 	This project is a micro-service template that can be used as the base for development of a new micro-service.
* 	This project the basic files and classes that has to be the part of all the microservices being developed given the rules and guidelines followed in BT.
* 	Majority of the files can be used directly with minor tweaking of data.


# Project Setup

**Template Customization**
1.	Clone the template project from develop.
2.	Remove the .git folder, inside the project root directory.
3.  Modify below files in order to make a valid project specific microservice.

	*	In pom.xml, update 'artifactId' to the project specific artifactId.
	*	Update MicroServiceApplication.java to project specific application class name.
	*	Class com.bt.ms.im.bptm.ThreadLocalBptmContextHolderStrategy.class, line 29, replace 'MICRO-SERVICES-TEMPLATE' with the artifactId(in CAPITALS).
	*	Class com.bt.ms.im.config.OpenApiConfiguration.java, update the Open API metadata.
	*	In application.properties, add property 'spring.service.start.url' with valid project start URL. Default value is /mnp/.
	*	Update Dockerfile, with yourself as the Maintainer and replace 'micro-services-template' with artifactId specified in pom.xml.
	
**Gitlab Repository**
*	Have a new git repository created in gitlab by git maintainer. Ensure below points.

	*	It has develop branch along with master.
	*	CI-CD is enabled (CI/CD option should be visible in left navigation tab).
	*	Go to Settings -> CICD -> Variables, check if CONFIG_MAP variable has been created. If not, have it created.
	*	Create a feature branch, from develop branch.
	*	Clone this repository in a local directory with the feature branch as the working branch and copy the updated microservice template project.
	*	Commit and push this code to the remote repository.
	
**BPTM Logs**
*	To enable BPTM logs for the service, update LogMessage.properties file. It is a set of instructions to do the BPTM logging got different calls. The set already mentioned in the template has to be copied for each type of REST method API exposed by the microservice.
*	Its structure is as follows: <artifactId in CAPITAL>_<REST method>-HTTP-ACCESS-**
*	Copy the entire set and update the artifactId and REST API method.

# Deployment

**Kubernetes Clusters**

| ENUM | Kubernetes cluster |
| --   | --                 |
| DB1 | Derby1 |
| DB2 | Derby2 |
| TY1 | Tinsley1 |
| TY2 | Tinsley2 |
	
**Directory Structure**
*	The deployment artifacts are maintained inside 'deployment' folder at root directory.
*	Inside this directory, keep .yaml file for each namespace with name as <namespace>.yaml having project specific details.
* 	These files are updated for each deployment as per the requirement along with the version of pom.xml.

**Run Pipeline**
*	To trigger the deployment, go to the gitlab remote repository, click on CI/CD.
*	Click on run pipeline, select the branch => develop
*	Enter below variables:

	| Variable Names | Pointer to the value |
	| --             | --                   |
	| CLUSTER_NAME | enum of the cluster for the current deployment |
	| ENV_NAME | environment name for current deployment (artefacts' folder as well as namespace)|

*	Trigger the pipeline.
*	This triggers 3 jobs:

	1.	First will run the sonar analysis on the code.
	2.	Second create the docker image and pushes it into docker harbor. Once this job is successful, it is advised to verify that latest image exist in the harbor.
	3. Third job is to do the deployment in kubernetes cluster. This job required manual trigger after the above job are success. Once this job runs successfully, please ensure that pods of the current deployment are up and running and do the basic sanity.
		
