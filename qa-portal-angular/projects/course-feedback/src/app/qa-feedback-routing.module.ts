import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation/trainee-course-evaluation.component';
import { TrainerEvaluationSummaryComponent } from './trainer-evaluation-summary/trainer-evaluation-summary.component';
import { TrainerEvaluationHistoryComponent } from './trainer-evaluation-history/trainer-evaluation-history.component';
import { TrainerFeedbackPageComponent } from './trainer-feedback-page/trainer-feedback-page.component';
import { TRAINEE_ROLE, TRAINER_ROLE } from '../../../portal-core/src/app/_common/models/portal-constants';
import { AppAuthGuard } from '../../../portal-core/src/app/_common/guards/app-auth-guard';
import { TraineeEvaluationSummaryComponent } from './trainee-evaluation-summary/trainee-evaluation-summary.component';
import { TrainerFeedbackHistoryComponent } from './trainer-feedback-history/trainer-feedback-history.component';

const routes: Routes = [
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
        path: 'evaluation/course/summary/:id',
        component: TrainerEvaluationSummaryComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINER_ROLE
          ]
        }
      },
      {
        path: 'history',
        component: TrainerFeedbackHistoryComponent,
        canActivate: [AppAuthGuard],
        data: {
          roles: [
            TRAINER_ROLE
          ]
        }
      },
      {
        path: 'course/:id',
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
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class QaFeedbackRoutingModule { }
