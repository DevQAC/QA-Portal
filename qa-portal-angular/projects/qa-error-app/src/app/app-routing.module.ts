import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {QaErrorPageComponent} from './qa-error-page/qa-error-page.component';
import {AppComponent} from './app.component';
import {TRAINEE_ROLE, TRAINER_ROLE, TRAINING_ADMIN_ROLE} from '../../../portal-core/src/app/_common/models/portal-constants';

const routes: Routes = [
  {
    path: 'error',
    component: AppComponent,
    children: [
      {
        path: '',
        component: QaErrorPageComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
