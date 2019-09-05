import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { ViewCvComponent } from './view-cv/view-cv.component';
import { CvSearchComponent } from './cv-search/cv-search.component';

import { AppAuthGuard } from '../../../portal-core/src/app/_common/guards/app-auth-guard';
import { TRAINEE_ROLE, TRAINING_ADMIN_ROLE } from '../../../portal-core/src/app/_common/models/portal-constants';

// TODO - Sarahs team to change component for "admin/search" route to their search component
const routes: Routes = [
  {
    path: 'trainee/current',
    component: ViewCvComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINEE_ROLE
      ]
    }
  },
  {
    path: 'admin/search',
    component: CvSearchComponent,
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
export class CvRoutingModule { }
