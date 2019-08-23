import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { ViewCvComponent } from './view-cv/view-cv.component';
import { CvSearchComponent } from './cv-search/cv-search.component';

const routes: Routes = [
  {
    path: 'test',
    component: ViewCvComponent
  },
  {
    path: 'admin/search',
    component: CvSearchComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class CvRoutingModule { }
