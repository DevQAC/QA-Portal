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
    ApplicationManagementComponent
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
    NewFormDialogComponent
  ]
})
export class QaAdminModule { }
