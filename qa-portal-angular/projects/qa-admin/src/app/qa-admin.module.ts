import { NgModule } from '@angular/core';

import { UserManagementComponent } from './user-management-console/user-management.component';
import { RoleChipsComponent } from './role-chips/role-chips.component';
import { QaCommonModule } from '../../../qa-common/src/app/qa-common.module';
import { AddUserDialogComponent } from './add-user-dialog/add-user-dialog.component';

import { CohortChipsComponent } from './cohort-chips/cohort-chips.component';
import { QaAdminRoutingModule } from './qa-admin-routing.module';
import { DeleteCohortDialogComponent } from './cohort-management/delete-cohort-dialog/delete-cohort-dialog.component';
import { CohortManagementComponent } from './cohort-management/cohort-management.component';
import { DeleteUserDialogComponent } from './user-management-console/delete-user-dialog/delete-user-dialog.component';
import { UpdateUserCohortDialogComponent } from './user-management-console/update-user-cohort-dialog/update-user-cohort-dialog.component';
import { UpdateUserRoleDialogComponent } from './user-management-console/update-user-role-dialog/update-user-role-dialog.component';


@NgModule({
  declarations: [
    UserManagementComponent,
    RoleChipsComponent,
    CohortChipsComponent,
    AddUserDialogComponent,
    CohortManagementComponent,
    DeleteCohortDialogComponent,
    DeleteUserDialogComponent,
    UpdateUserCohortDialogComponent,
    UpdateUserRoleDialogComponent,
  ],
  imports: [
    QaCommonModule,
    QaAdminRoutingModule
  ],
  providers: [],
  entryComponents: [
    // User
    AddUserDialogComponent,
    DeleteUserDialogComponent,
    UpdateUserCohortDialogComponent,
    UpdateUserRoleDialogComponent,
    // Cohort
    DeleteCohortDialogComponent
  ]
})
export class QaAdminModule { }
