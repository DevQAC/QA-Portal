# QA Portal Infrastructure


Project providing instructions for setting up local development environment to support running the QA Portal

**[1. Postgres](#postgres)**
- [Install Postgres](#install-postgres)
- [Create qa-portal database](#create-qa-portal-database)
- [Create keycloak database](#create-keycloak-database)

**[2. Keycloak](#keycloak)**
- [Install Keycloak](#install-keycloak)
- [Configure Postgres Module and Datasource](#configure-postgres-module-and-datasource)
- [Start Keycloak instance](#start-keycloak-instance)
- [Import Keycloak Realm files](#import-keycloak-realm-files)
- [Manage Keycloak users](#manage-keycloak-users)


**[3. MongoDB](#mongodb)**
- [Install MongoDB](#install-mongodb)
- [Create qa-portal-cv database](#create-qa-portal-cv-database)

<a name="postgres"></a>
## 1. Postgres

<a name="install-postgres"></a>
### 1.1. Install Postgres

Go to the Postgres download page (https://www.enterprisedb.com/downloads/postgres-postgresql-downloads) and select the download 
appropriate for your platform. Version 11 is currently being used by the QA Portal application

Once downloaded, run the installer, **supplying admin user and password (postgres / postgres)**

<a name="create-qa-portal-database"></a>
### 1.2. Create qa-portal database

Open pgadmin from the browser right click on Databases and select Create --> Database. Enter the database name as qa-portal and Save

The schema and tables will be created when the core-api project in the qa-portal-services folder is run (This is done by a library called Flyway)

<a name="create-keycloak-database"></a>
### 1.3. Create keycloak database

Open pgadmin from the browser right click on Databases and select Create --> Database. Enter the database name as keycloak and Save

The tables will be created and populated in Step 4 of the Keycloak instructions below.


<a name="keycloak"></a>
## 2. Keycloak

<a name="install-keycloak"></a>
### 2.1. Install Keycloak

a) Download Keycloak from https://www.keycloak.org/downloads.html. Version 6.0.1 is the current version being used by the QA Portal applications

b) Select the Server - Standalone server distribution ZIP download.

c) Unzip the downloaded file to a location of your choice (referenced as KEYCLOAK_HOME)

<a name="configure-postgres-module-and-datasource"></a>
### 2.2. Configure Postgres Module and Datasource

a) Copy the modules folder (and all it's contents) into your KEYCLOAK_HOME folder. This will set up your Postgres module

b) Copy the standalone folder (and it's contents) into your KEYCLOAK_HOME folder. This provides a standalone.xml file with a Postgres datasource set up for keycloak


<a name="start-keycloak-instance"></a>
### 2.3. Start Keycloak instance

a) From the KEYCLOAK_HOME/bin directory, run the ./standalone.bat script

b) Set up an admin user and password (admin / admin). See https://www.keycloak.org/docs/latest/getting_started/index.html 
sections 2.3 and 2.4 for full instructions 


<a name="import-keycloak-realm-files"></a>
### 2.4. Import Keycloak Realm files

a) Copy the exported_realms folder (and it's contents) into the KEYCLOAK_HOME/bin folder

b) Open a terminal at the KEYCLOAK_HOME/bin folder and run the following command

./standalone.bat  -Dkeycloak.migration.action=import -Dkeycloak.migration.provider=dir -Dkeycloak.migration.dir=exported_realms -Dkeycloak.migration.strategy=OVERWRITE_EXISTING

c) Once the command has executed, keycloak should be started (localhost:8080/auth/admin) and the qa-portal realm should be available (drop down at top left of side navigation on the 
admin page. You should also have a master realm available which is created by default)


<a name="manage-keycloak-users"></a>
### 2.5. Manage keycloak users

As part of the import in step 2.3, the following users should automatically be set up in your qa-portal realm.

adminclient1@qa.com with role of super-user and can perform administrative tasks through the portal

admin1@qa.com with role of training-admin

trainer1@qa.com  with roles training-manager, Cohort_CI_Intake_1 and Cohort_CI_Intake_2
trainer2@qa.com  with roles training-manager, Cohort_Java_Intake_1 and Cohort_Java_Intake_2

trainee1@qa.com ... trainee4:qa.com with roles training-user and Cohort_CI_Intake_1
trainee6@qa.com with roles training-user and Cohort_CI_Intake_2

Any additional new Portal users should be added through the QA Portal User management features of the administration application. This application will be available when you log into the portal as adminclient1@qa.com

<a name="mongodb"></a>
## 3. MongoDB

<a name="install-mongodb"></a>
### 3.1. Install MongoDb

Go to the MongoDb download page (https://docs.mongodb.com/manual/administration/install-community/) and select the download 
appropriate for your platform. Version 4.2 is currently being used by the QA Portal application.

Once downloaded, run the installer, **supplying admin user and password (mongo / mongo)**

<a name="create-qa-portal-cv-database"></a>
### 3.2. Create qa-portal-cv database

Open MongoDB Compass Community (or mongo shell) create database called qa-portal-cv and create a collection called cv-version
