import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { ViewCvComponent } from './view-cv/view-cv.component';

const routes: Routes = [
  {
    path: 'test',
    component: ViewCvComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CvRoutingModule { }
