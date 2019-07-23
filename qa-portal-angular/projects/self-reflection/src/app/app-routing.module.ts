import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CohortTraineesComponent } from './cohort-trainees/cohort-trainees.component'

const routes: Routes = [{
  path: 'trainee',
  children: []
}, {
  path: 'trainer',
  children: [{
    path: 'cohort/trainees', component: CohortTraineesComponent
  }
  ]
},
{
  path: 'admin',
  children: []
}

]

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
