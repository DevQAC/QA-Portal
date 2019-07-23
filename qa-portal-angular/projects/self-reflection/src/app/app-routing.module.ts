import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TraineeReflectionHistoryComponent } from './trainee-reflection-history/trainee-reflection-history.component';

const routes: Routes = [
  {
    path: 'trainee',
    children: [
      {
        path: 'selfreflections', component: TraineeReflectionHistoryComponent
      }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
