import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {PortalHomeSharedModule} from '../../../portal-home/src/app/app.module';
import {QaErrorSharedModule} from '../../../qa-error-app/src/app/app.module';
import {SelfReflectionSharedModule} from '../../../self-reflection/src/app/app.module';

const routes: Routes = [
  // Add routes for new application here
  {
  path: 'qa/portal/selfreflection',
  loadChildren: () => SelfReflectionSharedModule
  },
  {
    path: 'qa/portal',
    loadChildren: () => QaErrorSharedModule
  },
  {
    path: 'qa/portal/home',
    loadChildren: () => PortalHomeSharedModule
  },
  {
    path: 'qa',
    children: [
      {path: '**', redirectTo: '/qa/portal/home'}
    ]
  },
  {
    path: '', redirectTo: '/qa/portal/home', pathMatch: 'full'
  }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes),
    PortalHomeSharedModule.forRoot(),
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
