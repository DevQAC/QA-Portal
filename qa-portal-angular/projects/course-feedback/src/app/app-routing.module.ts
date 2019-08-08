import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppComponent} from './app.component';
import { TrainerEvaluationHistoryComponent } from './trainer-evaluation-history/trainer-evaluation-history.component';

const routes: Routes = [{
  path: 'home', component: AppComponent,
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
