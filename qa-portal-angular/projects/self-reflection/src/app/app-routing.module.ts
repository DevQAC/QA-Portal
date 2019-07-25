import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CohortSummaryComponent } from './cohort-summary/cohort-summary.component';



const routes: Routes = [
  {
    path: 'admin',
    children: [
    {
      path: 'cohorts', component: CohortSummaryComponent
    }
    ]
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
