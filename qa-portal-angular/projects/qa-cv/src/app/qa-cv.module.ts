import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { QaCommonModule } from 'projects/qa-common/src/app/app.module';
import { CvRoutingModule } from './cv-routing.module';
import { ViewCvComponent } from './view-cv/view-cv.component';


@NgModule({
  declarations: [
    ViewCvComponent
  ],
  imports: [
    BrowserModule,
    CvRoutingModule,
    QaCommonModule
  ],
  providers: []
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