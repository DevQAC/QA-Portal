import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {FeedbackPageComponent} from './end-of-course-eval/feedback-page/feedback-page.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';
import {TrainerEvaluationSummaryComponent} from '../app/trainer-evaluation-summary/trainer-evaluation-summary.component';
import {TrainerEvaluationHistoryComponent} from './trainer-evaluation-history/trainer-evaluation-history.component';
import {TrainerFeedbackPageComponent} from './trainer-feedback/trainer-feedback-page/trainer-feedback-page.component';

const routes: Routes = [
  {
    path: 'feedback',
    children: [
      {
        path: 'trainee',
        children: [
          {
            path: 'evaluation/history',
            component: CourseEvaluationComponent
          },
          {
            path: 'evaluation',
            component: FeedbackPageComponent
          }
        ]
      },
      {
        path: 'trainer',
        children: [
          {
            path: 'evaluation/history',
            component: TrainerEvaluationHistoryComponent
          },
          {
            path: 'evaluation/course/summary',
            component: TrainerEvaluationSummaryComponent
          },
          {
            path: 'current',
            component: TrainerFeedbackPageComponent
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
