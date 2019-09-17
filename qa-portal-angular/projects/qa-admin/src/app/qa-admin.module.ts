import { NgModule } from '@angular/core';

import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { RoleChipsComponent } from './role-chips/role-chips.component';
import { QaCommonModule } from '../../../qa-common/src/app/qa-common.module';
import { AddUserDialogComponent } from './add-user-dialog/add-user-dialog.component';
import { DelUserConfirmDialogComponent } from './del-user-confirm-dialog/del-user-confirm-dialog.component';
import { EditUserDialogComponent } from './edit-user-dialog/edit-user-dialog.component';

import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './_common/services/in-memory-data.service';
import { HttpClientModule } from '@angular/common/http';
import { CohortChipsComponent } from './cohort-chips/cohort-chips.component';
import { QaAdminRoutingModule } from './qa-admin-routing.module';
import { DeleteCohortDialogComponent } from './cohort-management/delete-cohort-dialog/delete-cohort-dialog.component';
import { CohortManagementComponent } from './cohort-management/cohort-management.component';


@NgModule({
  declarations: [
    UserManagementConsoleComponent,
    RoleChipsComponent,
    CohortChipsComponent,
    AddUserDialogComponent,
    DelUserConfirmDialogComponent,
    EditUserDialogComponent,
    CohortManagementComponent,
    DeleteCohortDialogComponent,
  ],
  imports: [
    QaCommonModule,
    QaAdminRoutingModule,
    HttpClientModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { passThruUnknownUrl: true }
    )
  ],
  providers: [],
  entryComponents: [
    AddUserDialogComponent,
    DelUserConfirmDialogComponent,
    EditUserDialogComponent,
    DeleteCohortDialogComponent
  ]
})
export class QaAdminModule { }
