import {BrowserModule} from '@angular/platform-browser';
import {NgModule, ModuleWithProviders} from '@angular/core';

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
import {RetrieveTrainerEvaluationHistoryService} from './trainer-evaluation-history/services/retrieve-trainer-evaluation-history.service';
import {InstructorZoneTitleComponent} from './trainer-evaluation-history/instructor-zone-title/instructor-zone-title.component';
import {SearchFormComponent} from './trainer-evaluation-history/search-form/search-form.component';
import {FormTypeService} from './_common/services/form-type.service';
import {FeedbackPageComponent} from './end-of-course-eval/feedback-page/feedback-page.component';
import {TrainerFeedbackPageComponent} from './trainer-feedback/trainer-feedback-page/trainer-feedback-page.component';
import {FeedbackQuestionComponent} from './trainer-feedback/feedback-question/feedback-question.component';
import {ProblemReporterComponent} from './trainer-feedback/problem-reporter/problem-reporter.component';
import { QaFormsModule } from 'projects/qa-forms';
import {FeedbackService} from './trainer-feedback/_common/services/feedback.service';
import {EvaluationService} from './_common/services/evaluation-service';
import { TraineeEvaluationSummaryComponent } from './trainee-evaluation-summary/trainee-evaluation-summary.component';



@NgModule({
  declarations: [
    AppComponent,
    TrainerEvaluationSummaryComponent,
    TableComponentComponent,
    CourseInfoComponent,
    InstructorZoneTitleComponent,
    SearchBoxComponent,
    CourseEvaluationComponent,
    EvaluationTableComponent,
    TrainerEvaluationHistoryComponent,
    InstructorZoneTitleComponent,
    SearchFormComponent,
    FeedbackPageComponent,
    TrainerFeedbackPageComponent,
    FeedbackQuestionComponent,
    ProblemReporterComponent,
    TraineeEvaluationSummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    HttpClientModule,
    QaFormsModule
  ],
  providers: [
    RetrieveTrainerEvaluationHistoryService,
    SearchFormComponent,
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
