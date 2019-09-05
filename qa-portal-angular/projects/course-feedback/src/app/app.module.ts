import {BrowserModule} from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';
import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import {TrainerEvaluationSummaryComponent} from './trainer-evaluation-summary/trainer-evaluation-summary.component';
import {TableComponentComponent} from './trainer-evaluation-summary/table-component/table-component.component';
import {CourseInfoComponent} from './trainer-evaluation-summary/course-info/course-info.component';
import {SearchBoxComponent} from './trainer-evaluation-summary/search-box/search-box.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';
import {EvaluationTableComponent} from './evaluation-table/evaluation-table.component';
import {TrainerEvaluationHistoryComponent} from './trainer-evaluation-history/trainer-evaluation-history.component';
import {HttpClientModule} from '@angular/common/http';
import {TrainerCourseHistoryService} from './_common/services/trainer-course-history.service';
import {FormTypeService} from './_common/services/form-type.service';
import {TraineeCourseEvaluationComponent} from './trainee-course-evaluation/trainee-course-evaluation.component';
import {TrainerFeedbackPageComponent} from './trainer-feedback-page/trainer-feedback-page.component';
import {QaFormsModule} from 'projects/qa-forms';
import {EvaluationService} from './_common/services/evaluation-service';
import {TraineeEvaluationSummaryComponent} from './trainee-evaluation-summary/trainee-evaluation-summary.component';
import {TraineeEvaluationSummaryService} from './trainee-evaluation-summary/services/trainee-evaluation-summary.service';
import {FeedbackService} from './trainer-feedback-page/_common/services/feedback.service';
import { TrainerFeedbackHistoryComponent } from './trainer-feedback-history/trainer-feedback-history.component';
import {InstructorZoneTitleComponent} from './trainer-evaluation-summary/instructor-zone-title/instructor-zone-title.component';


@NgModule({
  declarations: [
    AppComponent,
    TrainerEvaluationSummaryComponent,
    TableComponentComponent,
    CourseInfoComponent,
    SearchBoxComponent,
    CourseEvaluationComponent,
    EvaluationTableComponent,
    TrainerEvaluationHistoryComponent,
    TraineeCourseEvaluationComponent,
    TrainerFeedbackPageComponent,
    TraineeEvaluationSummaryComponent,
    TrainerFeedbackHistoryComponent,
    InstructorZoneTitleComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    HttpClientModule,
    QaFormsModule
  ],
  providers: [
    TrainerCourseHistoryService,
    TraineeEvaluationSummaryService,
    FormTypeService,
    FeedbackService,
    EvaluationService
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}

@NgModule({})
export class CourseFeedbackSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AppModule,
      providers: []
    };
  }
}
