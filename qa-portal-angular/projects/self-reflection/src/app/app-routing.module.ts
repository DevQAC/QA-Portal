import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { SelfReflectionHistoryComponent } from './self-reflection-history/self-reflection-history.component';

const routes: Routes = [
  {
    path: 'trainee',
    children: [
      {
        path: 'selfreflections', component: SelfReflectionHistoryComponent
      }
  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes), ],
  exports: [RouterModule]
})
export class AppRoutingModule { }
