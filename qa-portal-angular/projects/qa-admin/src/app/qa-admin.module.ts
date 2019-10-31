import { NgModule } from '@angular/core';

import { UserManagementComponent } from './user-management-console/user-management.component';
import { QaCommonModule } from '../../../qa-common/src/app/qa-common.module';

import { QaAdminRoutingModule } from './qa-admin-routing.module';
import { CohortManagementComponent } from './cohort-management/cohort-management.component';
import { DeleteUserDialogComponent } from './user-management-console/delete-user-dialog/delete-user-dialog.component';
import { UpdateUserCohortDialogComponent } from './user-management-console/update-user-cohort-dialog/update-user-cohort-dialog.component';
import { UpdateUserRoleDialogComponent } from './user-management-console/update-user-role-dialog/update-user-role-dialog.component';
import { AddUserDialogComponent } from './user-management-console/add-user-dialog/add-user-dialog.component';
import { CohortDetailComponent } from './cohort-detail/cohort-detail.component';
import { DragDropModule } from '@angular/cdk/drag-drop';

import { CalendarModule, DateAdapter } from 'angular-calendar';
import { adapterFactory } from 'angular-calendar/date-adapters/date-fns';

import { UserDetailComponent } from './user-detail/user-detail.component';
import { AddCourseDialogComponent } from './cohort-detail/add-course-dialog/add-course-dialog.component';
import { CourseManagementComponent } from './course-management/course-management.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';
import { NewCourseDialogComponent } from './course-management/new-course-dialog/new-course-dialog.component';
import { TechnologyManagementComponent } from './technology-management/technology-management.component';
import { TechnologyDetailComponent } from './technology-detail/technology-detail.component';
import { NewCategoryDialogComponent } from './technology-management/new-category-dialog/new-category-dialog.component';
import { NewCohortDialogComponent } from './cohort-management/new-cohort-dialog/new-cohort-dialog.component';
import { FormManagementComponent } from './form-management/form-management.component';
import { NewFormDialogComponent } from './form-management/new-form-dialog/new-form-dialog.component';
import { FormDetailComponent } from './form-detail/form-detail.component';
import { ApplicationManagementComponent } from './application-management/application-management.component';
import { ApplicationDetailComponent } from './application-detail/application-detail.component';
import { NewAppDialogComponent } from './application-management/new-app-dialog/new-app-dialog.component';
import { AppProjectManagementComponent } from './app-project-management/app-project-management.component';
import { AppProjectDetailComponent } from './app-project-detail/app-project-detail.component';
import { NewProjectDialogComponent } from './app-project-management/new-project-dialog/new-project-dialog.component';
import { DeletePageConfirmDialogComponent } from './app-project-detail/delete-page-confirm-dialog/delete-page-confirm-dialog.component';
import { LocationManagementComponent } from './location-management/location-management.component';
import { LocationDetailComponent } from './location-detail/location-detail.component';
import { NewLocationDialogComponent } from './location-management/new-location-dialog/new-location-dialog.component';
import { RoleManagementComponent } from './role-management/role-management.component';
import { NewRoleDialogComponent } from './role-management/new-role-dialog/new-role-dialog.component';
import { RoleDetailComponent } from './role-detail/role-detail.component';
import { EditCourseDialogComponent } from './cohort-detail/edit-course-dialog/edit-course-dialog.component';

@NgModule({
  declarations: [
    UserManagementComponent,
    AddUserDialogComponent,
    CohortManagementComponent,
    DeleteUserDialogComponent,
    UpdateUserCohortDialogComponent,
    UpdateUserRoleDialogComponent,
    CohortDetailComponent,
    UserDetailComponent,
    AddCourseDialogComponent,
    CourseManagementComponent,
    CourseDetailComponent,
    NewCourseDialogComponent,
    TechnologyManagementComponent,
    TechnologyDetailComponent,
    NewCategoryDialogComponent,
    NewCohortDialogComponent,
    FormManagementComponent,
    NewFormDialogComponent,
    FormDetailComponent,
    ApplicationManagementComponent,
    ApplicationDetailComponent,
    NewAppDialogComponent,
    AppProjectManagementComponent,
    AppProjectDetailComponent,
    NewProjectDialogComponent,
    DeletePageConfirmDialogComponent,
    LocationManagementComponent,
    LocationDetailComponent,
    NewLocationDialogComponent,
    RoleManagementComponent,
    NewRoleDialogComponent,
    RoleDetailComponent,
    EditCourseDialogComponent
  ],
  imports: [
    QaCommonModule,
    QaAdminRoutingModule,
    DragDropModule,
    CalendarModule.forRoot({
      provide: DateAdapter,
      useFactory: adapterFactory
    })
  ],
  providers: [],
  entryComponents: [
    AddUserDialogComponent,
    DeleteUserDialogComponent,
    UpdateUserCohortDialogComponent,
    UpdateUserRoleDialogComponent,
    AddCourseDialogComponent,
    NewCourseDialogComponent,
    NewCategoryDialogComponent,
    NewCohortDialogComponent,
    NewFormDialogComponent,
    NewAppDialogComponent,
    NewProjectDialogComponent,
    DeletePageConfirmDialogComponent,
    NewLocationDialogComponent,
    NewRoleDialogComponent,
    EditCourseDialogComponent
  ]
})
export class QaAdminModule { }
