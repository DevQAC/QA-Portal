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

**Step4 - Create a user**

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


**Step3 - Create schema and tables**

Create the schema for the qa-portal database by running the training-schema.ddl file from the qa-portal-infra/postgres folder


**Step4 - Populate tables**

Run the insert scripts from the qa-portal-infra/postgres folder as follows

application-inserts.sql<br>
department-inserts.sql<br>
role-inserts.sql<br>
app_menu_item-inserts.sql<br>
dept_role-inserts.sql<br>
dept_role_application-inserts.sql<br>
self_rating_question-inserts.sql<br>
self_reflection_status-inserts.sql
