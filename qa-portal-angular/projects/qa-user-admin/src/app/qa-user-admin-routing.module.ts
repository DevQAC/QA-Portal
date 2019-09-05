import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppAuthGuard } from '../../../portal-core/src/app/_common/guards/app-auth-guard';
import { TRAINING_ADMIN_ROLE } from '../../../portal-core/src/app/_common/models/portal-constants';
import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';

const routes: Routes = [
  {
    path: 'console/users',
    component: UserManagementConsoleComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINING_ADMIN_ROLE
      ]
    }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class QaUserAdminRoutingModule { }
