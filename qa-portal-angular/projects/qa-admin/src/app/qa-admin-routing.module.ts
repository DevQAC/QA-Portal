import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { UserManagementComponent } from './user-management-console/user-management.component';
import { AppAuthGuard } from 'projects/portal-core/src/app/_common/guards/app-auth-guard';
import { SUPER_USER } from 'projects/portal-core/src/app/_common/models/portal-constants';
import { CohortManagementComponent } from './cohort-management/cohort-management.component';
import { CohortDetailComponent } from './cohort-detail/cohort-detail.component';
import { UserDetailComponent } from './user-detail/user-detail.component';
import { CourseManagementComponent } from './course-management/course-management.component';
import { CourseDetailComponent } from './course-detail/course-detail.component';
import { TechnologyManagementComponent } from './technology-management/technology-management.component';
import { TechnologyDetailComponent } from './technology-detail/technology-detail.component';
import { FormManagementComponent } from './form-management/form-management.component';
import { FormDetailComponent } from './form-detail/form-detail.component';
import { ApplicationManagementComponent } from './application-management/application-management.component';

const routes: Routes = [
  {
    path: 'manage/users',
    component: UserManagementComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/users/:id',
    component: UserDetailComponent,
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
  },
  {
    path: 'manage/cohorts/:id',
    component: CohortDetailComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/courses',
    component: CourseManagementComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/courses/:id',
    component: CourseDetailComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/technologies',
    component: TechnologyManagementComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/technologies/:id',
    component: TechnologyDetailComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/forms',
    component: FormManagementComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/forms/:id',
    component: FormDetailComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
  {
    path: 'manage/applications',
    component: ApplicationManagementComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        SUPER_USER
      ]
    }
  },
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QaAdminRoutingModule { }
