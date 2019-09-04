import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { RoleChipsComponent } from './role-chips/role-chips.component';
import { QaCommonModule } from 'projects/qa-common/src/app/app.module';
import { AddUserDialogComponent } from './add-user-dialog/add-user-dialog.component';
import { DelUserConfirmDialogComponent } from './del-user-confirm-dialog/del-user-confirm-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    UserManagementConsoleComponent,
    RoleChipsComponent,
    AddUserDialogComponent,
    DelUserConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule
  ],
  providers: [],
  bootstrap: [AppComponent],
  entryComponents: [
    AddUserDialogComponent,
    DelUserConfirmDialogComponent
  ]
})
export class AppModule { }

@NgModule({})
export class QaAdminSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AppModule,
      providers: []
    };
  }
}