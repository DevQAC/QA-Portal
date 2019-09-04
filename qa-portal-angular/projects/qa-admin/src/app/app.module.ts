import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { RoleChipsComponent } from './role-chips/role-chips.component';
import { QaCommonModule } from 'projects/qa-common/src/app/app.module';

@NgModule({
  declarations: [
    AppComponent,
    UserManagementConsoleComponent,
    RoleChipsComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule
  ],
  providers: [],
  bootstrap: [AppComponent]
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