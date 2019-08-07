import {NgModule} from '@angular/core';
import {Routes,RouterModule} from '@angular/router';
import {AppComponent} from './app.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';

const routes: Routes = [{
    path: 'home',
    component: AppComponent
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
export class AppRoutingModule {}
