import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent} from './app.component';
import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation/trainee-course-evaluation.component';

const routes: Routes = [
  {
    path: 'feedback/end-of-course-evaluation', component: TraineeCourseEvaluationComponent
  },
  {
    path: 'feedback/home', component: AppComponent
  }];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
