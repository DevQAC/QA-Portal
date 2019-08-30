import {BrowserModule} from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import {CvRoutingModule} from './cv-routing.module';
import {ViewCvComponent} from './view-cv/view-cv.component';
import {CvProfileComponent} from './cv-profile/cv-profile.component';
import {CvWorkExpWrapperComponent} from './cv-work-exp-wrapper/cv-work-exp-wrapper.component';
import {CvHobbiesComponent} from './cv-hobbies/cv-hobbies.component';
import {CvQualisWrapperComponent} from './cv-qualis-wrapper/cv-qualis-wrapper.component';
import {CvSkillsComponent} from './cv-skills/cv-skills.component';
import {CvWorkExpComponent} from './cv-work-exp-wrapper/cv-work-exp/cv-work-exp.component';
import {CvQualisComponent} from './cv-qualis-wrapper/cv-qualis/cv-qualis.component';
import {MatNativeDateModule} from '@angular/material';
import {CVSearchFilterService} from './cv-search/services/cv-search-filter.service';
import {CvSearchComponent} from './cv-search/cv-search.component';
import {CvFeedbackComponent} from './cv-feedback/cv-feedback.component';
import {CvCardBaseComponent} from './cv-card-base/cv-card-base.component';
import {CVSearchHistoryService} from './cv-search/services/cv-search.service';
import { SubmitConfirmDialogComponent } from './view-cv/submit-confirm-dialog/submit-confirm-dialog.component';



@NgModule({
  declarations: [
    ViewCvComponent,
    CvProfileComponent,
    CvWorkExpWrapperComponent,
    CvHobbiesComponent,
    CvQualisWrapperComponent,
    CvSkillsComponent,
    CvWorkExpComponent,
    CvQualisComponent,
    CvSearchComponent,
    CvFeedbackComponent,
    CvCardBaseComponent,
    SubmitConfirmDialogComponent
  ],
  imports: [
    BrowserModule,
    CvRoutingModule,
    QaCommonModule,
    MatNativeDateModule
  ],
  providers: [
    CVSearchHistoryService,
    CVSearchFilterService
  ],
  entryComponents: [
    SubmitConfirmDialogComponent
  ]
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
