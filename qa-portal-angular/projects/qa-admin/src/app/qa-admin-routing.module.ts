import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { AppAuthGuard } from 'projects/portal-core/src/app/_common/guards/app-auth-guard';
import { TRAINING_ADMIN_ROLE } from 'projects/portal-core/src/app/_common/models/portal-constants';

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
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QaAdminRoutingModule { }
