import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { QaCommonModule } from 'projects/qa-common/src/app/app.module';
import { CvRoutingModule } from './cv-routing.module';
import { ViewCvComponent } from './view-cv/view-cv.component';
import { CvSearchComponent } from './cv-search/cv-search.component';
import {CVSearchHistoryService} from './cv-search/services/cv-search-history.service';


@NgModule({
  declarations: [
    ViewCvComponent,
    CvSearchComponent
  ],
  imports: [
    BrowserModule,
    CvRoutingModule,
    QaCommonModule
  ],
  providers: [CVSearchHistoryService]
})
export class QaCvModule { }

@NgModule({})
export class QaCvSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: QaCvModule,
      providers: []
    };
  }

}