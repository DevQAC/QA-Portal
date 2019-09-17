import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { QaErrorPageComponent } from './qa-error-page/qa-error-page.component';
import { AppComponent } from './app.component';

const routes: Routes = [
  {
    path: '',
    component: AppComponent,
    children: [
      {
        path: '',
        component: QaErrorPageComponent
      }
    ]
  },
  {
    path: '404',
    redirectTo: '/error?errorMsg=Page not found. Please try again.'
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QaErrorRoutingModule { }
