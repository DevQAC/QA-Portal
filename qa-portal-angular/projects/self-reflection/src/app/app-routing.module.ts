import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TrainerReflectionComponent} from './trainer-reflection/trainer-reflection.component';
import {TrainerCohortsComponent} from './trainer-cohorts/trainer-cohorts.component';
import {CohortSummaryComponent} from './cohort-summary/cohort-summary.component';
import {TraineeReflectionComponent} from './trainee-reflection/trainee-reflection.component';

const routes: Routes = [
  {
    path: 'trainee',
    children: [
      {
        path: 'selfreflection', component: TraineeReflectionComponent
      }
    ]
  },
  {
    path: 'trainer',
    children: [
      {
        path: 'cohorts', component: TrainerCohortsComponent
      },
      {
        path: 'selfreflection/:id', component: TrainerReflectionComponent
      }
    ]
  },
  {
    path: 'admin',
    children: [
      {
        path: 'cohorts', component: CohortSummaryComponent
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
