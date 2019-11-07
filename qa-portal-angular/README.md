# QA Portal - Angular

**[1. Overview](#overview)**

**[2. Developer Guide](#developer-guide)**
- [2.1. Portal Core Application](#portal-core)
- [2.2. QA Common Application](#qa-common-app)
- [2.3. QA Error Application](#qa-error-app)
- [2.4. QA Home Application](#qa-home-app)
- [2.5. Self Reflection Application](#qa-reflection-app) 
- [2.6. Create Portal Application](#create-portal-application) 
- [2.7. Develop Portal Components and Services](#develop-components-and-services)

**[3. Build and Run Portal](#build-run-portal)**
- [3.1. Pre-requisites](#build-run-portal-prereq)
- [3.2. Start portal](#start-portal)

<a name="overview"></a>
## 1. Overview

The projects in this repository provide the UI components of the QA Portal application. At the top level is an angular workspace containing each of the applications that are part of the QA Portal. These applications are all configured from the angular.json file defined in the workspace.

The portal applications are located in the projects folder of the workspace. Any new applications to be added to the portal should be added using the following command from the root folder of the workspace.

ng generate application app-name –-routing 

This will generate the "app-name" application in the projects directory and update the angular.json file with its configuration.

The projects directory contains the following applications:

**portal-core** - The core application of the Portal. This manages the main navigation toolbar, sidebar menu, error handling and security 

**qa-common** - For common UI components reused across multiple portal applications. Does not have routing configured

**qa-error-app** - Provides error components and their routing. These error routes will be initiated from the central error handler service in the portal-core application

**portal-home** - Landing page when a user logs into the portal. 

**trainee-reflection-app** - Application for creating, submitting and viewing Trainee Self reflection forms

The other applications in the projects folder currently have no functionality, but have been added to demonstrate navigation between the various portal applications and the configuration to include an application in the portal.

NOTE: The package.json has a "start" script. Use this to run the application using the angular CLI

npm start

This will use the proxy.conf.json file to route calls to external services. If the proxy.conf.json is not included in the start up configuration the external service calls will fail.

<a name="developer-guide"></a>
## 2. Developer Guide

The following provides a more detailed explanation of each of the portal projects, how they are structured, how to add a new application to the portal, and the associated development tasks.

<a name="portal-core"></a>
### 2.1. Portal Core Application


#### 2.1.1. Security

Security is managed by Keycloak with the application integrated with the Keycloak Identity and Access Management Provider through an angular-keycloak adapter. The portal-core app.module.ts has an APP_INITIALIZER provider that triggers the initializer function in app-init.ts. This initiates the keycloak authentication. If the user is not authenticated, then the application is redirected to the keycloak provider and a log in page for the QA Portal is presented to the user.

Once the user has entered their username and password, they are authenticated by the keycloak provider, and if successful a JWT is issued. Keycloak then redirects the application to the url originally specified by the user prior to the keyloak redirection. 

The angular-keycloak adapter by default provides an Http interceptor that will add the JWT as an Authorization header to any Http requests sent from the angular application. This behaviour can be disabled for specific urls if necessary.

**STILL TO BE DEVELOPED** - A user could enter a url into the browser address bar that they do not have sufficient privileges to access. In order to prevent this, we need to implement an Angular Guard and configure this to each of the routes in our application. The Guard will be invoked before the route is activated to prevent unauthorized access.


#### 2.1.2. Portal Navigation

The portal-core project manages navigation between applications within the portal and also manages which applications are available in the portal for a user.

The portal-header components top navigation toolbar presents the user with the applications (grouped by department) available to the user based on the users roles. These roles were determined during keycloak authentication. The list of available applications is retrieved by a call to the portal-application-api applications service.

Once the navigation toolbar has been loaded, the user navigates between applications by selecting an application from the menu. There are 2 events initiated when selecting an application from the menu

a) The side navigation menu items will update to contain the menu items for this selected application. This is triggered in app.component.ts, through a subscription to router navigation events. Whenever a route changes, the url of the new route is passed to the subscriber and triggers an update to the contents of the side navigation pane.

b) The main content pane will display the home page of the selected application. This is default angular behaviour based on the routerLink anchor in the portal-header.component.html file
 

#### 2.1.3. Application Navigation

Navigation within an application is controlled from links within the application or from the menu items on the side navigation pane. 

Each application has an `app-routing.module.ts` file to define it's navigation routes.
 
The portal-core `app-routing.module.ts` loads the routing modules of each of the portal projects and all routing is controlled from the portal-core project. The intention is to lazy load these routing modules but at the moment they are directly loaded as there appears to be a problem with lazy loading from other applications in Angular 8. 


#### 2.1.4. Error Handling

The portal-core application manages error handling through the  `QaErrorHandlingService`. This can be referenced from any of the portal applications. For an example of using the error handler service, see `self-reflection-form.component.ts`

The error handling service will redirect any severe errors to an error page advising the user that there is a serious issue with the application and if the issue continues to contact support.

Any application errors that can be resolved by the user will be displayed using the Toastr service.

<a name="qa-common-app"></a>
### 2.2. QA Common Application

Any UI components that can be reused across the various Portal applications should be placed in the QA Common Application. This application does not have routing configured. 

It also imports all angular modules that are likely to be needed by the various portal applications, so each portal application should import `QACommonModule` in their app.module.ts for access to these modules.

See rated-question component in the QA Common application for an example of a reusable component. 

For an example of using one of these components see the self-reflection-form component of the Trainee Reflection application. 

<a name="qa-error-app"></a>
### 2.3. QA Error Application

The Error application provides a basic error component and it's associated routing. The routing to this component should only be triggered through the `QaErrorHandlerService` in the portal-core application.

This application is still under development.

<a name="qa-home-app"></a> 
### 2.4. Portal Home Application

This should be the default application when a user logs into the QA Portal. This application is still under development. 

<a name="qa-reflection-app"></a> 
### 2.5. Trainee Reflection Application

This has been developed to provide guidance for the future development of Portal applications. The application has basic functionality, but has examples of the following.

- Invoke external REST services.

    All REST service calls should be made using the Angular HttpClient service. There are examples of GET and POST requests in the `self-reflection-form-state.service.ts` and `rated-questions.service.ts` services. For more information on HttpClient see https://angular.io/guide/http#httpclient.

- Include components from the QA Common application.

    The self-reflection-form.component.html has an example of including the self rated question common component using the `<app-rated-question>` element. This demonstrates how to pass data to the component to control how it is rendered.

- Use the error handling service

    All error handling should be delegated to the `QaErrorHandlerService` so that all errors are processed in a consistent manner. The self-reflection-form.component.ts has examples of invoking the QaErrorHandlerService after service call failures.

- Define routes

    Routes are defined in the `app-routing.module.ts`, as per the angular standard convention.

<a name="create-portal-application"></a>
### 2.6. Create Portal Application

***NOTE: Replace any references to {appname} with the actual name of your application***

1. Generate a new application in the QA Portal workspace. In `qa-portal-angular/` run the following command:

    ```bash
    ng generate application {appname} --routing
    ```

    This will create a new Angular application with routing in the `projects/` folder, and its configuration will be added to the `angular.json` file.

2. Update the name of the new application's app and routing module to match the name you've chosen. This while not required will help differentiate it from other applications in the project.

    So the modules located here:
    ```
    qa-portal-angular/projects/{appname}/src/app/app.module.ts

    qa-portal-angular/projects/{appname}/src/app/app-routing.module.ts
    ```
    Should be renamed to:
    ```
    qa-portal-angular/projects/{appname}/src/app/{appname}.module.ts

    qa-portal-angular/projects/{appname}/src/app/{appname}-routing.module.ts
    ```
    Do this to the module definitions in these files too.


3. Add the `QACommonModule` to your new application by adding it to the imports in the {appname}.module.ts file.
    ```javascript
    // qa-portal-angular/projects/{appname}/src/app/{appname}-routing.module.ts
    imports: [
        QaCommonModule,
        ...
    ]
    ```

4. Connect the new application into the Portal Core by adding it to the Portal Core routing module routes array.
    ```javascript
    // qa-portal-angular/projects/portal-core/src/app/app-routing.module.ts
    const routes: Routes = [
        ...,
        {
            path: 'path/to/new/application/routing/module',
            loadChildren: () => import('../../../{appname}/src/app/{appname}.module').then(mod => mod.{appname}Module)
        }
    ];
    ```

5. Create application folder structure.

    Under the new application's root folder `projects/{appname}` the following structure is recommended for code that will be used by multiple components in the application

    ```
        src/app/_common/
        src/app/_common/models/
        src/app/_common/services/
    ```

<a name="develop-components-and-services"></a>
### 2.7. Developing Portal Components and Services

1. Generate a new component. From the workspace root folder run the following

    ```bash
    ng generate component {compname} --project={appname}
    ```

2. Within the `projects/{appname}/src/app/{compname}` folder add the following folders as required.
    ```
    models/
    services/
    validators/
    ```

#### 2.7.1. Component Guidelines

When developing a `{compname}.component.ts` the following are useful guidelines to follow

-  No manipulation of DOM elements - Any DOM updates should occur through the binding to the directives in the {compname}.component.html file

-  All data required by this component view should be contained in a view model class located in the ./models folder. This has the advantage of simplicity, but also allows us to easily store a components "state" in session or local storage if required.

-  Any Http calls should be performed in a service class (in the `./services` folder), and the component should use the service class to call REST services. By doing this, the mapping of data returned from the service into a format that can be added to our view model, can be done outside the component. If this is all done in the component it can become bloated and difficult to maintain and test.

-  Error handling needs to be included for all service calls. This should be a simple addition of an error function, in the subscription to the service, and delegate the error handling to the QaErrorHandlerService.

-  Generally a component will have to retrieve some data to prepopulate UI components. This functionality should be added to the ngOnInit method, with the component implementing the OnInit interface

-  For scenarios as described in e) where data is being loaded asynchronously it can be useful to add a spinner to the UI. The boolean to control display of the spinner can be defined outside of the view model, and should be set to false as soon as all data has bee loaded. See self-reflection-history.component.ts (and html) for an example.

-  Any validation required for the component should be placed in a separate validate class, which should be injected into the component. Again this is to separate functionality and prevent the component from getting bloated and difficult to maintain and test.
 
 
#### 2.7.2. Service Guidelines

-  Where to put the service code

    Services that can be used by all Portal applications should be defined in the portal-core project in the `src/app/_common/services` folder.

    Services that are used in a specific application, but by multiple components in that application should be placed in the `projects/{appname}/src/app/_common/services` folder.

    Services that used only in a specific component should be placed in the `projects/{appname}/src/app/{compname}/services` folder.

-  Each service class should be decorated with the `@Injectable` annotation. This ensures that dependencies can be injected into this service. If this annotation is not specified, then any dependencies (e.g. HttpClient) will fail to be injected when the service is instantiated.

-  For communication between unrelated components (i.e. they don't have a parent child relationship), services can be used along with subscriptions to rxjs Subjects (or BejaviourSubjects). See the ApplicationSelectionService class in the portal-core application for an example.

<a name="build-run-portal"></a>
## 3. Building and Running Portal

<a name="build-run-portal-prereq"></a>
### 3.1. Pre-Requisites

1.  A local keycloak instance and Postgres DB has been installed and configured. See instructions in 
https://github.com/DevQAC/QA-Portal/blob/development/qa-portal-infra/README.md

2.  An instance of portal-application-api services are running locally. See instructions in
https://github.com/DevQAC/QA-Portal/blob/development/qa-portal-services/README.md

<a name="start-portal"></a>
### 3.2. Start QA Portal

1. Clone the qa-portal-angular repo using command.

    See instructions in https://github.com/DevQAC/QA-Portal/blob/master/README.md

2. From the base project folder install dependencies using **npm**.
    ```bash
    npm install
    ```
3. From the base project folder start the angular application.
    ```bash
    npm start
    ```
