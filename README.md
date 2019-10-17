# QA Portal

The QA Portal architecture consists of the following components

-   [Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/) for server side microservices

-   [Angular 8](https://angular.io/docs) for client side user interfaces

-   [Postgres](https://www.postgresql.org/docs/11/index.html) for relational data.

-   [Mongo](https://docs.mongodb.com/) for document based non-relational data

-   [Keycloak](https://www.keycloak.org/archive/documentation-6.0.html) for authentication and access control

## Contents

**[1. Architecture](#architecture)**
- [High Level diagram](#high-level-diagram)
- [Architecture Overview](#architecture-overview)

**[2. Developer Environment Setup](#developer-environment-setup)**

**[3. Source Control](#source-control)**
- [Assumptions](#assumptions)
- [Project Startup](#project-startup)
- [QA Portal Development](#qa-portal-development)


## Architecture

### High Level diagram
![](./docs/image/QA_Portal_High_Level_Architecture_Diagram.png)

### Architecture Overview


## Developer Environment Setup

1. Clone this repository into your local workspace. See instructions in the [Source Control](#source-control) section below for instructions.
2. Set up Postgres, Keycloak and Mongo DB. See [Local development environment setup](https://github.com/DevQAC/QA-Portal/qa-portal-infra/README.md) for instructions.
3. Build and run the Spring Boot microservices. See [Build and run microservices](https://github.com/DevQAC/QA-Portal/qa-portal-services/README.md) for instructions
4. Build and run the Angular client application. See [Build and run angular application](https://github.com/DevQAC/QA-Portal/qa-portal-angular/README.md) for instructions


## Source Control

Source code for the QA Portal will be managed in Github. The QA Portal repository is located at 

https://github.com/DevQAC/QA-Portal<br>


### Assumptions

a) It is assumed you already have a Git account set up. If this is not the case, access http://www.github.com and register for an account

b) It is also assumed that you have Git installed on your development machine (either command line or through your IDE). If not, download and install a Git client (or Git plugin for your IDE)


### Project Startup

#### Clone the Git repository

Create a top level (workspace) folder to store the source code. From this folder run the following command

git clone https://github.com/DevQAC/QA-Portal.git<br>


#### Pull the development branches

Pull the development branch into your local workspace as follows

git fetch

git checkout -t origin/development


### QA Portal Development

When starting work on a new Jira task, for each repository that you will have to amend, create a local feature branch (i.e. if you need to change both qa-portal-angular and qa-portal-services repos then you'll need to create a local feature branch for each of them)

#### Create local feature branch

Make sure you are in the development branch of the repository and get the latest changes by running the following commands

cd {path to repo folder in your local workspace}

git checkout development

git pull

Create a new feature branch (name it by Jira Task number and title of change) as follows

git branch feature/JIRA-XXXX-Task-title

Switch to the new branch

git checkout feature/JIRA-XXXX-Task-title

Once you've created your local feature branch start making your changes to the feature branch(es). After you've made amendments to your code, it is advisable to push your local feature branch to the remote repo and create a pull request. This can be done as follows


#### Push your local feature branch(es)

Repeat the following for each of your feature branches to push local branch to the remote repository

git push origin feature/JIRA-XXXX-Task-title


#### Create a Pull Request

Log in to Github

Navigate to the repository branches page.

https://github.com/DevQAC/QA-Portal/branches

Select your feature branch

Press the 'New Pull Request' button

Leave the base as 'development'

Press the compare button and select your feature branch

Press the 'Create Pull Request' button.


#### Complete development

Continue to develop your feature until you are happy that it is working and has been developed in accordance with the projects development guidelines, with unit and integration tests.

Ensure that your feature branch also contains the latest changes that have been merged into the development branch, and works with these changes. You can do this by merging the latest development branch code into your local feature branch. This is best done with your IDE as merging from the command line when there are conflicts can be cumbersome.

At this point, push the changes from your local branch to the branch in the remote repository and inform the development lead that your feature is ready for review.


#### Pull Request Review

The development lead should then review your changes, ensure that there are no conflicts with the development branch, and that your work adheres to the project standards. 

If there are any issues, these should be documented on the Pull request and returned to the developer to address. The review cycle is then repeated.

#### Approval and Merge

Once the development lead is happy that your changes are acceptable, they should approve the Pull request and merge the changes into the development branch. The feature branches should then be deleted from the repository and you should delete the feature branches from your local workspace.
