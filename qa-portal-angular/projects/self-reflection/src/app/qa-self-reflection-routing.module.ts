import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {CohortSummaryComponent} from './cohort-summary/cohort-summary.component';
import {TraineeReflectionComponent} from './trainee-reflection/trainee-reflection.component';
import {SelfReflectionHistoryComponent} from './self-reflection-history/self-reflection-history.component';
import {CohortTraineesComponent} from './cohort-trainees/cohort-trainees.component';
import {TraineeNewReflectionComponent} from './trainee-new-reflection/trainee-new-reflection.component';
import {TRAINEE_ROLE, TRAINER_ROLE, TRAINING_ADMIN_ROLE} from '../../../portal-core/src/app/_common/models/portal-constants';
import {AppAuthGuard} from '../../../portal-core/src/app/_common/guards/app-auth-guard';
import {TrainerReflectionComponent} from './trainer-reflection/trainer-reflection.component';

const routes: Routes = [
  {
    path: 'trainee/new', component: TraineeNewReflectionComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINEE_ROLE
      ]
    }
  },
  {
    path: 'trainee/history', component: SelfReflectionHistoryComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINEE_ROLE
      ]
    }
  },
  {
    path: 'trainee/:id', component: TraineeReflectionComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINEE_ROLE
      ]
    }
  },
  {
    path: 'trainer/cohorts/trainees', component: CohortTraineesComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINER_ROLE
      ]
    }
  },
  {
    path: 'trainer/trainee/:id', component: TrainerReflectionComponent,
    canActivate: [AppAuthGuard],
    data: {
      roles: [
        TRAINER_ROLE
      ]
    }
  },
  {
    path: 'admin/cohorts/summary', component: CohortSummaryComponent,
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
export class AppRoutingModule {
}
