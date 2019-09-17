import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { RoleChipsComponent } from './role-chips/role-chips.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { AddUserDialogComponent } from './add-user-dialog/add-user-dialog.component';
import { DelUserConfirmDialogComponent } from './del-user-confirm-dialog/del-user-confirm-dialog.component';
import { EditUserDialogComponent } from './edit-user-dialog/edit-user-dialog.component';

import { HttpClientInMemoryWebApiModule } from 'angular-in-memory-web-api';
import { InMemoryDataService } from './_common/services/in-memory-data.service';
import { HttpClientModule } from '@angular/common/http';
import { CohortChipsComponent } from './cohort-chips/cohort-chips.component';
import { CohortManagementComponent } from './cohort-management/cohort-management.component';
import { DeleteCohortDialogComponent } from './cohort-management/delete-cohort-dialog/delete-cohort-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    UserManagementConsoleComponent,
    RoleChipsComponent,
    CohortChipsComponent,
    AddUserDialogComponent,
    DelUserConfirmDialogComponent,
    EditUserDialogComponent,
    CohortManagementComponent,
    DeleteCohortDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    HttpClientModule,
    HttpClientInMemoryWebApiModule.forRoot(
      InMemoryDataService, { passThruUnknownUrl: true }
    )
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    AddUserDialogComponent,
    DelUserConfirmDialogComponent,
    EditUserDialogComponent,
    DeleteCohortDialogComponent
  ]
})
export class AppModule {
}

@NgModule({})
export class QaAdminSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AppModule,
      providers: []
    };
  }
}
