import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TraineeCourseEvaluationComponent} from './trainee-course-evaluation/trainee-course-evaluation.component';
import {TrainerEvaluationSummaryComponent} from '../app/trainer-evaluation-summary/trainer-evaluation-summary.component';
import {TrainerEvaluationHistoryComponent} from './trainer-evaluation-history/trainer-evaluation-history.component';
import {TrainerFeedbackPageComponent} from './trainer-feedback/trainer-feedback-page/trainer-feedback-page.component';
import {TRAINEE_ROLE, TRAINER_ROLE} from '../../../portal-core/src/app/_common/models/portal-constants';
import {AppAuthGuard} from '../../../portal-core/src/app/_common/guards/app-auth-guard';
import {TraineeEvaluationSummaryComponent} from './trainee-evaluation-summary/trainee-evaluation-summary.component';

const routes: Routes = [
  {
    path: 'feedback',
    children: [
      {
        path: 'trainee',
        children: [
          {
            path: 'evaluation/history',
            component: TraineeEvaluationSummaryComponent,
            canActivate: [AppAuthGuard],
            data: {
              roles: [
                TRAINEE_ROLE
              ]
            }
          },
          {
            path: 'evaluation/:id',
            component: TraineeCourseEvaluationComponent,
            canActivate: [AppAuthGuard],
            data: {
              roles: [
                TRAINEE_ROLE
              ]
            }
          }
        ]
      },
      {
        path: 'trainer',
        children: [
          {
            path: 'evaluation/history',
            component: TrainerEvaluationHistoryComponent,
            canActivate: [AppAuthGuard],
            data: {
              roles: [
                TRAINER_ROLE
              ]
            }
          },
          {
            path: 'evaluation/course/summary',
            component: TrainerEvaluationSummaryComponent,
            canActivate: [AppAuthGuard],
            data: {
              roles: [
                TRAINER_ROLE
              ]
            }
          },
          {
            path: 'current',
            component: TrainerFeedbackPageComponent,
            canActivate: [AppAuthGuard],
            data: {
              roles: [
                TRAINER_ROLE
              ]
            }
          }
        ]
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
