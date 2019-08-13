import {NgModule} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';
import { TrainerEvaluationSummaryComponent} from '../app/trainer-evaluation-summary/trainer-evaluation-summary.component';

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
    path:'train',
    component:TrainerEvaluationSummaryComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
