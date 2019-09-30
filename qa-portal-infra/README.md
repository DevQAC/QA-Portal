QA Portal Infrastructure
---

Instructions for setting up local environment to support running the QA Portal

Postgres
---

**Step1 - Install Postgres**

Go to the Postgres download page (https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and select the download 
appropriate for your platform

Once downloaded, run the installer, supplying admin user and password (postgres / postgres)

**Step2 - Create qa-portal database**

Open pgadmin and from the browser right click on Databases and select Create --> Database. Enter the database name as qa-portal and Save

The schema and tables will be created when the core-api project in the qa-portal-services folder is run (This is done by a library called Flyway)


**Step3 - Create keycloak database**

Open pgadmin and from the browser right click on Databases and select Create --> Database. Enter the database name as keycloak and Save

The tables will be created and populated when the migratwhen the core-api project in the qa-portal-services folder is run (This is done by a library called Flyway)



Keycloak
---

**Step1 - Install Keycloak**

a) Download Keycloak from https://www.keycloak.org/downloads.html

b) Select the Server - Standalone server distribution ZIP download.

c) Unzip the downloaded file to a location of your choice (referenced as KEYCLOAK_HOME)


** Step2 - Add Postgres Module and Postgres Datasource configuration

a) Copy the modules folder (and all it's contents) into your KEYCLOAK_HOME folder. This will set up your Postgres module

b) Copy the standalone folder (and it's contents) into your KEYCLOAK_HOME folder. This provides a standalone.xml file with a Postgres datasource set up for keycloak



**Step2 - Start Keycloak instance**

a) From the KEYCLOAK_HOME/bin directory, run the ./standalone.bat script

b) Set up an admin user and password (admin / admin). See https://www.keycloak.org/docs/latest/getting_started/index.html 
sections 2.3 and 2.4 for full instructions 


**Step3 - Import files in exported_realms folder**

a) Copy the exported_realms folder (and it's contents) into the KEYCLOAK_HOME/bin folder

b) Open a terminal at the KEYCLOAK_HOME/bin folder and run the following command

./standalone.bat  -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=dir -Dkeycloak.migration.dir=exported_realms -Dkeycloak.migration.strategy=OVERWRITE_EXISTING

c) Once the command has executed, keycloak should be started and the qa-portal realm should be available (drop down at top left of side navigation on the 
admin page. You should also have a master realm available which is created by default)

**Step4 - Create users**

As part of the import in step 3, the following users should be set up in your qa-portal realm.

adminclient1@qa.com with role of super-user and can perform administrative tasks through the portal

admin1@qa.com with role of training-admin

trainer1@qa.com  with roles training-manager, Cohort_CI_Intake_1 and Cohort_CI_Intake_2
trainer2@qa.com  with roles training-manager, Cohort_Java_Intake_1 and Cohort_Java_Intake_2

trainee1@qa.com ... trainee4:qa.com with roles training-user and Cohort_CI_Intake_1
trainee6@qa.com with roles training-user and Cohort_CI_Intake_2

The following details how to create a user and add roles in the keycloak admin console, i

New Portal users should be added through the QA Portal User management features of the administration application. This application will be available when you log into the portal as adminclient1@qa.com


MongoDB
---

**Step1 - Install MongoDb**

Go to the MongoDb download page (https://docs.mongodb.com/manual/administration/install-community/) and select the download 
appropriate for your platform

Once downloaded, run the installer, supplying admin user and password (mongo / mongo)

**Step2 - Create qa-portal-cv database**

Open MongoDB Compass Community (or mongo shell) create database called qa-portal-cv and create a collection called cv-version
