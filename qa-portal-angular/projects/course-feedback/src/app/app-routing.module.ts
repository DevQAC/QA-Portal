import {NgModule} from '@angular/core';
import {Routes, RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';
import { TrainerEvaluationSummaryComponent} from '../app/trainer-evaluation-summary/trainer-evaluation-summary.component';
import { TrainerEvaluationHistoryComponent } from './trainer-evaluation-history/trainer-evaluation-history.component';

const routes: Routes = [
  {
    path: 'feedback/home',
    component: AppComponent
  },
  {
    path: 'course-evaluation',
    component: CourseEvaluationComponent
  },
  {
    path: 'train',
    component:  TrainerEvaluationSummaryComponent
  },
  {
    path: 'trainer',
    children: [
      {
        path: 'evaluationhistory', component: TrainerEvaluationHistoryComponent
      }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
