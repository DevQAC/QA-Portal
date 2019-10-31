import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AppAuthGuard } from './_common/guards/app-auth-guard';
import { PortalApplicationHomeComponent } from './portal-application-home/portal-application-home.component';
import { PortalHomeComponent } from './portal-home/portal-home.component';

const routes: Routes = [
  { // Common portal applications home page. Keep this at the top!
    path: 'qa/portal/:dep',
    pathMatch: 'full',
    component: PortalApplicationHomeComponent
  },
  // Add routes for new application here
  {
    path: 'qa/portal/training/feedback',
    loadChildren: () => import('../../../course-feedback/src/app/qa-feedback.module').then(mod => mod.QaFeedbackModule)
  },
  {
    path: 'qa/portal/training/self-reflection',
    loadChildren: () => import('../../../self-reflection/src/app/qa-self-reflection.module').then(mod => mod.QaSelfReflectionModule)
  },
  {
    path: 'qa/portal/training/cv',
    loadChildren: () => import('../../../qa-cv/src/app/qa-cv.module').then(mod => mod.QaCvModule)
  },
  {
    path: 'qa/portal/admin',
    loadChildren: () => import('../../../qa-admin/src/app/qa-admin.module').then(mod => mod.QaAdminModule)
  },
  {
    path: 'error',
    loadChildren: () => import('../../../qa-error-app/src/app/qa-error.module').then(mod => mod.QaErrorModule)
  },
  {
    path: '',
    pathMatch: 'full',
    component: PortalHomeComponent
  },
  {
    path: '**',
    redirectTo: 'error/404'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
  ],
  providers: [AppAuthGuard],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
