import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { TrainerReflectionComponent } from './trainer-reflection/trainer-reflection.component';
import { AppComponent } from 'projects/qa-error-app/src/app/app.component';


const routes: Routes = [
  {
    path: 'trainer',
    children: [
      {
        path: 'selfreflection/:id', component: TrainerReflectionComponent
      }
    ]
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {
}
