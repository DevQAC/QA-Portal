import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TraineeReflectionComponent } from './trainee-reflection/trainee-reflection.component';


const routes: Routes = [
  {
    path: 'trainee',
    children: [
      {
        path: 'selfreflection/current', component: TraineeReflectionComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
