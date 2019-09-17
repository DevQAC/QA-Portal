import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserManagementConsoleComponent } from './user-management-console/user-management-console.component';
import { AppAuthGuard } from 'projects/portal-core/src/app/_common/guards/app-auth-guard';
import { SUPER_USER } from 'projects/portal-core/src/app/_common/models/portal-constants';
import { CohortManagementComponent } from './cohort-management/cohort-management.component';

const routes: Routes = [
  {
    path: 'manage/users',
    component: UserManagementConsoleComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/cohorts',
    component: CohortManagementComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QaAdminRoutingModule { }
