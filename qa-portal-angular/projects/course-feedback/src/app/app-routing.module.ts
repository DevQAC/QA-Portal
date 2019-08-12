import { NgModule } from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { AppComponent} from './app.component';
import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation/trainee-course-evaluation.component';
import { FeedbackPageComponent } from './end-of-course-eval/feedback-page/feedback-page.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';

const routes: Routes = [
  {
    path: 'feedback/end-of-course-evaluation', component: FeedbackPageComponent
  },
  {
    path: 'feedback/home', component: AppComponent
  },
  {
    path: 'course-evaluation',
    component: CourseEvaluationComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
