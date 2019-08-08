# QA Portal - Angular

## Overview

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

## Developer Guide

The following provides a more detailed explanation of each of the portal projects, how they are structured, how to add a new application to the portal, and the associated development tasks.

### Portal Core Application


#### Security

Security is managed by Keycloak with the application integrated with the Keycloak Identity and Access Management Provider through an angular-keycloak adapter. The portal-core app.module.ts has an APP_INITIALIZER provider that triggers the initializer function in app-init.ts. This initiates the keycloak authentication. If the user is not authenticated, then the application is redirected to the keycloak provider and a log in page for the QA Portal is presented to the user.

Once the user has entered their username and password, they are authenticated by the keycloak provider, and if successful a JWT is issued. Keycloak then redirects the application to the url originally specified by the user prior to the keyloak redirection. 

The angular-keycloak adapter by default provides an Http interceptor that will add the JWT as an Authorization header to any Http requests sent from the angular application. This behaviour can be disabled for specific urls if necessary.

**STILL TO BE DEVELOPED** - A user could enter a url into the browser address bar that they do not have sufficient privileges to access. In order to prevent this, we need to implement an Angular Guard and configure this to each of the routes in our application. The Guard will be invoked before the route is activated to prevent unauthorized access.


#### Portal Navigation

The portal-core project manages navigation between applications within the portal and also manages which applications are available in the portal for a user.

The portal-header components top navigation toolbar presents the user with the applications (grouped by department) available to the user based on the users roles. These roles were determined during keycloak authentication. The list of available applications is retrieved by a call to the core-api applications service.

Once the navigation toolbar has been loaded, the user navigates between applications by selecting an application from the menu. There are 2 events initiated when selecting an application from the menu

a) The side navigation menu items will update to contain the menu items for this selected application. This is triggered in app.component.ts, through a subscription to router navigation events. Whenever a route changes, the url of the new route is passed to the subscriber and triggers an update to the contents of the side navigation pane.

b) The main content pane will display the home page of the selected application. This is default angular behaviour based on the routerLink anchor in the portal-header.component.html file
 

#### Application Navigation

Navigation within an application is controlled from links within the application or from the menu items on the side navigation pane. 

Each application has an app-routing.module.ts file to define it's navigation routes.
 
The portal-core app-routing.module.ts loads the routing modules of each of the portal projects and all routing is controlled from the portal-core project. The intention is to lazy load these routing modules but at the moment they are directly loaded as there appears to be a problem with lazy loading from other applications in Angular 8. 


#### Error Handling

The portal-core application manages error handling through the QaErrorHandlingService (qa-error-handling.service.ts). This can be referenced from any of the portal applications. For an example of using the error handler service, see self-reflection-form.component.ts

The error handling service will redirect any severe errors to an error page advising the user that there is a serious issue with the application and if the issue continues to contact support.

Any application errors that can be resolved by the user will be displayed using the Toastr service.

### QA Common Application

Any UI components that can be reused across the various Portal applications should be placed in the QA Common Application. This application does not have routing configured. 

It also imports all angular modules that are likely to be needed by the various portal applications, so each portal application should import QACommonModule in their app.module.ts for access to these modules.

See rated-question component in the QA Common application for an example of a reusable component. 

For an example of using one of these components see the self-reflection-form component of the Trainee Reflection application. 

### QA Error Application

The Error application provides a basic error component and it's associated routing. The routing to this component should only be triggered through the QaErrorHandlerService in the portal-core application.

This application is still under development.

 
### Portal Home Application

This should be the default application when a user logs into the QA Portal. This application is still under development. 


### Trainee Reflection Application

This has been developed to provide guidance for the future development of Portal applications. The application has basic functionality, but has examples of the following

a) Invoke external REST services

All REST service calls should be made using the Angular HttpClient service. There are examples of GET and POST requests in the self-reflection-form-state.service.ts and rated-questions.service.ts services. For more information on HttpClient see https://angular.io/guide/http#httpclient

b) Include components from the QA Common application

The self-reflection-form.component.html has an example of including the self rated question common component using the <app-rated-question> element. This demonstrates how to pass data to the component to control how it is rendered.

c) Use the error handling service

All error handling should be delegated to the QaErrorHandlerService so that all errors are processed in a consistent manner. The self-reflection-form.component.ts has examples of invoking the QaErrorHandlerService after service call failures.

d) Define routes

Routes are defined in the app-routing.module.ts, as per the angular standard convention.


### Adding a new application to the Portal

NOTE: Replace any references to {appname} and {AppName}SharedModule with the actual name of your application and module

a) Generate a new application in the QA Portal workspace. In the top level folder run the following command

ng generate application {appname} --routing

This will create a new Angular application with routing in the projects folder, and its configuration will be added to the angular.json file.

b) Create a new module with a name specific to this application, and return an empty providers array, as the services in this application should only be accessed from components within this application. 

To achieve this, edit the app.module.ts file in the projects/{appname}/src/app folder as follows:

Add the following to the end of the file

@NgModule({})<br>
export class {AppName}SharedModule {<br>
  static forRoot(): ModuleWithProviders {<br>
    return {<br>
      ngModule: AppModule,<br>
      providers: []<br>
    };<br>
  }<br>
}<br>

c) Add the QACommonModule to your new application by adding it to the imports in the app.module.ts file.

d) Make your new application module available in the portal-core application. In the projects/portal-core/src/app folder, edit the app.module.ts file, adding this new module at the end of the list of imports

  imports: [<br>
    QaCommonModule,<br>
    AppRoutingModule,<br>
    .......,<br>
    {AppName}SharedModule
    

e) Make the routes in your new application available from portal-core application. In the projects/portal-core/src/app folder, edit the app-routing.module.ts file, adding a new route on the line before the comment

// Add routes for new application here

the new route object should be as follows

{<br>
path: 'qa/portal/{department}',<br>
loadChildren: () => {AppName}SharedModule<br> 
}

where {department} should be replaced by training, hr, etc

f) Create application folder structure 

Under the application root folder (projects/{appname}) the following structure is recommended for code that will be used by multiple components in the application

_common<br>
_common/models<br>
_common/services<br>
_common/validators<br>


### Developing Portal Components and Services

a) Generate a new component. From the workspace root folder run the following

ng generate component {compname} --project={appname}

b) Within the projects/{appname}/src/app/{compname} folder add the following folders

models<br>
services<br>
validators<br>


#### Component Guidelines

When developing a {compname}.component.ts the following are useful guidelines to follow

a) No manipulation of DOM elements - Any DOM updates should occur through the binding to the directives in the {compname}.component.html file

b) All data required by this component view should be contained in a view model class located in the ./models folder. This has the advantage of simplicity, but also allows us to easily store a components "state" in session or local storage if required.

c) Any Http calls should be performed in a service class (in the ./services folder), and the component should use the service class to call REST services. By doing this, the mapping of data returned from the service into a format that can be added to our view model, can be done outside the component. If this is all done in the component it can become bloated and difficult to maintain and test.

d) Error handling needs to be included for all service calls. This should be a simple addition of an error function, in the subscription to the service, and delegate the error handling to the QaErrorHandlerService.

e) Generally a component will have to retrieve some data to prepopulate UI components. This functionality should be added to the ngOnInit method, with the component implementing the OnInit interface

f) For scenarios as described in e) where data is being loaded asynchronously it can be useful to add a spinner to the UI. The boolean to control display of the spinner can be defined outside of the view model, and should be set to false as soon as all data has bee loaded. See self-reflection-history.component.ts (and html) for an example.

g) Any validation required for the component should be placed in a separate validate class, which should be injected into the component. Again this is to separate functionality and prevent the component from getting bloated and difficult to maintain and test.
 
 
#### Service Guidelines

a) Where to put the service code

Services that can be used by all Portal applications should be defined in the portal-core project in the src/app/_common/services folder.

Services that are used in a specific application, but by multiple components in that application should be placed in the projects/{appname}/src/app/_common/services folder.

Services that used only in a specific component should be placed in the projects/{appname}/src/app/{compname}/services folder.

b) Each service class should be decorated with the @Injectable annotation. This ensures that dependencies can be injected into this service. If this annotation is not specified, then any dependencies (e.g. HttpClient) will fail to be injected when the service is instantiated.

c) For communication between unrelated components (i.e. they don't have a parent child relationship), services can be used along with subscriptions to rxjs Subjects (or BejaviourSubjects). See the ApplicationSelectionService class in the portal-core application for an example.


## Building and Running Portal

### Pre-Requisites

a) A local keycloak instance and Postgres DB has been installed and configured. See instructions in 
https://github.com/DevQAC/QA-Portal/blob/development/qa-portal-infra/README.md

b) An instance of core-api services are running locally. See instructions in
https://github.com/DevQAC/QA-Portal/blob/development/qa-portal-services/README.md


### Build and Run QA Portal

a) Clone the qa-portal-angular repo using command

See instruction in https://github.com/DevQAC/QA-Portal/blob/master/README.md

b) From the base project folder install dependencies using npm

npm install

c) From the base project folder start the angular application

npm start
