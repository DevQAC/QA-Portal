QA Portal Infrastructure
---

Instructions for setting up local environment to support running the QA Portal

Keycloak
---

**Step1 - Install Keycloak**

a) Download Keycloak from https://www.keycloak.org/downloads.html

b) Select the Server - Standalone server distribution ZIP download.

c) Unzip the downloaded file to a location of your choice (referenced as KEYCLOAK_HOME)

**Step2 - Start Keycloak instance**

a) From the KEYCLOAK_HOME/bin directory, run the ./standalone.bat script

b) Set up an admin user and password (admin / admin). See https://www.keycloak.org/docs/latest/getting_started/index.html 
sections 2.3 and 2.4 for full instructions 


**Step3 - Import qa-portal realm settings**

a) From the keycloak Admin console (http://localhost:8080/auth/admin/), select the Import option

b) Select the qa-portal-realm.json file

c) Press the Import button

d) Once the import has completed, the qa-portal realm should be available (drop down at top left of side navigation on the 
admin page. Should also have a master realm available which is created by default)

**Step4 - Create users**

This is currently a manual step, but the intention is to migrate to a Postgres DB for storing keycloak data, which should enable loading of users from exported database file. For now, the following users will have to be created manually through the keycloak admin console

admin1@qa.com assign role training-admin

trainer1@qa.com  assign roles training-manager, Cohort_CI_Intake_1 and Cohort_CI_Intake_2
trainer2@qa.com  assign roles training-manager, Cohort_Java_Intake_1 and Cohort_Java_Intake_2
trainer3@qa.com  assign roles training-manager and Cohort_Scala_Intake_1)

trainee1@qa.com ... trainee5:qa.com assign roles training-user and Cohort_CI_Intake_1
trainee6@qa.com ... trainee10@qa.com assign roles training-user and Cohort_CI_Intake_2
trainee11@qa.com ... trainee15@qa.com assign roles training-user and Cohort_Java_Intake_1
trainee16@qa.com ... trainee20@qa.com assign roles training-user and Cohort_Java_Intake_2​
trainee21@qa.com ... trainee25@qa.com assign roles training-user and Cohort_Scala_Intake_1)

The following details how to create a user and add roles in the keucloak admin console

a) After selecting the qa-portal realm, select the Users option

b) Click the Add user button (far right of the page)

c) Provide a username, and optionally provide Email, First name and last name. Leave the other settings at their defaults and press Save

d) Further options are displayed for the user. Select the Credentials tab.

e) Enter a password and confirmation. Set Temporary to OFF, which means that you won't have to immediately change your password when you log
in to the Qa Portal application

f) Add a role to the user by selecting the Role Mappings tab. In the Available Roles list of the Realm Roles, select one or more
roles for your user and press Add Selected button


Postgres
---

**Step1 - Install Postgres**

Go to the Postgres download page (https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and select the download 
appropriate for your platform

Once downloaded, run the installer, supplying admin user and password (postgres / postgres)

**Step2 - Create qa-portal database**

Open pgadmin and from the browser right click on Databases and select Create --> Database. Enter the database name as qa-portal and Save

The schema and tables will be created when the core-api project in the qa-portal-services folder is run (This is done by a library called Flyway)


MongoDB
---

**Step1 - Install MongoDb**

Go to the MongoDb download page (https://docs.mongodb.com/manual/administration/install-community/) and select the download 
appropriate for your platform

Once downloaded, run the installer, supplying admin user and password (mongo / mongo)

**Step2 - Create qa-portal-cv database**

Open MongoDB Compass Community (or mongo shell) create database called qa-portal-cv and create a collection called cv-version
