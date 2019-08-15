import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {AppComponent} from './app.component';
import {TRAINEE_ROLE, TRAINER_ROLE, TRAINING_ADMIN_ROLE} from '../../../portal-core/src/app/_common/models/portal-constants';

const routes: Routes = [
  {
    path: '',
    component: AppComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
