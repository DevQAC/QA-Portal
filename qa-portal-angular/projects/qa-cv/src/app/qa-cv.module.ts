import {BrowserModule} from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import {CvRoutingModule} from './cv-routing.module';
import {ViewCvComponent} from './view-cv/view-cv.component';
import {CvProfileComponent} from './cv-profile/cv-profile.component';
import {CvWorkExpWrapperComponent} from './cv-work-exp-wrapper/cv-work-exp-wrapper.component';
import {CvHobbiesComponent} from './cv-hobbies/cv-hobbies.component';
import {CvQualificationWrapperComponent} from './cv-qualis-wrapper/cv-qualification-wrapper.component';
import {CvSkillsComponent} from './cv-skills/cv-skills.component';
import {CvWorkExpComponent} from './cv-work-exp-wrapper/cv-work-exp/cv-work-exp.component';
import {CvQualificationComponent} from './cv-qualis-wrapper/cv-qualification/cv-qualification.component';
import {MatNativeDateModule} from '@angular/material';
import {CVSearchFilterService} from './cv-search/services/cv-search-filter.service';
import {CvSearchComponent} from './cv-search/cv-search.component';
import {CvCardBaseComponent} from './cv-card-base/cv-card-base.component';
import {CVSearchHistoryService} from './cv-search/services/cv-search.service';
import {SubmitConfirmDialogComponent} from './view-cv/submit-confirm-dialog/submit-confirm-dialog.component';
import {PdfViewerModule} from 'ng2-pdf-viewer';
import {ViewCvService} from './view-cv/services/view-cv.service';
import {ViewCvStateManagerService} from './view-cv/services/view-cv-state-manager.service';
import {ViewCvPageDataService} from './view-cv/services/view-cv-page-data.service';

@NgModule({
  declarations: [
    ViewCvComponent,
    CvProfileComponent,
    CvWorkExpWrapperComponent,
    CvHobbiesComponent,
    CvQualificationWrapperComponent,
    CvSkillsComponent,
    CvWorkExpComponent,
    CvQualificationComponent,
    CvSearchComponent,
    CvCardBaseComponent,
    SubmitConfirmDialogComponent,
    CvCardBaseComponent
  ],
  imports: [
    BrowserModule,
    CvRoutingModule,
    QaCommonModule,
    MatNativeDateModule,
    PdfViewerModule
  ],
  providers: [
    CVSearchHistoryService,
    CVSearchFilterService,
    ViewCvService,
    ViewCvStateManagerService,
    ViewCvPageDataService
  ],
  entryComponents: [
    SubmitConfirmDialogComponent
  ]
})
export class QaCvModule {
}

@NgModule({})
export class QaCvSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: QaCvModule,
      providers: []
    };
  }

}
