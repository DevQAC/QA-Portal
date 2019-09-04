import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { QaCommonModule } from 'projects/qa-common/src/app/app.module';
import { RoleChipsComponent } from './role-chips/role-chips.component';

@NgModule({
  declarations: [
    UserManagementConsoleComponent,
    RoleChipsComponent
  ],
  imports: [
    BrowserModule,
    QaCommonModule
  ],
  providers: []
})

export class QaUserAdminModule { }

@NgModule({})
export class QaUserAdminSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: QaUserAdminModule,
      providers: []
    };
  }

}
