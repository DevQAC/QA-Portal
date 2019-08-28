import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { CvRoutingModule } from './cv-routing.module';
import { ViewCvComponent } from './view-cv/view-cv.component';
import { CvProfileComponent } from './cv-profile/cv-profile.component';
import { CvWorkExpWrapperComponent } from './cv-work-exp-wrapper/cv-work-exp-wrapper.component';
import { CvHobbiesComponent } from './cv-hobbies/cv-hobbies.component';
import { CvQualisWrapperComponent } from './cv-qualis-wrapper/cv-qualis-wrapper.component';
import { CvSkillsComponent } from './cv-skills/cv-skills.component';
import { CvWorkExpComponent } from './cv-work-exp-wrapper/cv-work-exp/cv-work-exp.component';
import { CvQualisComponent } from './cv-qualis-wrapper/cv-qualis/cv-qualis.component';
import { MatNativeDateModule } from '@angular/material';
import { CVSearchFilterService } from './cv-search/services/cv-search-filter.service';
import { CVSearchHistoryService } from './cv-search/services/cv-search-history.service';
import { CvSearchComponent } from './cv-search/cv-search.component';
import { CvFeedbackComponent } from './cv-feedback/cv-feedback.component';
import { CvCardBaseComponent } from './cv-card-base/cv-card-base.component';


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
    CvSearchComponent
    CvFeedbackComponent,
    CvCardBaseComponent
  ],
  imports: [
    BrowserModule,
    CvRoutingModule,
    QaCommonModule,
    MatNativeDateModule
  ],
  providers: [CVSearchHistoryService,CVSearchFilterService ]
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