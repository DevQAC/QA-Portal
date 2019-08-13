import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CourseEvaluationComponent } from './course-evaluation/course-evaluation.component';
import { EvaluationTableComponent } from './evaluation-table/evaluation-table.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation/trainee-course-evaluation.component';
import { WhyThisCourseQuestionComponent } from './trainee-course-evaluation/why-this-course-question/why-this-course-question.component';
import { WhyWithQAComponent } from './trainee-course-evaluation/why-with-qa/why-with-qa.component';
import { FormTypeService } from './_common/services/form-type.service';
import { FeedbackPageComponent } from './end-of-course-eval/feedback-page/feedback-page.component';
import { QuestionCategoryComponent } from './end-of-course-eval/question-category/question-category.component';
import { QuestionComponent } from './end-of-course-eval/question/question.component';
import { TrainerFeedbackPageComponent } from './trainer-feedback/trainer-feedback-page/trainer-feedback-page.component';
import { FeedbackQuestionComponent } from './trainer-feedback/feedback-question/feedback-question.component';
import { StepperComponent } from './trainer-feedback/stepper/stepper.component';

@NgModule({
  declarations: [
    AppComponent,
    CourseEvaluationComponent,
    EvaluationTableComponent,
    TraineeCourseEvaluationComponent,
    WhyThisCourseQuestionComponent,
    WhyWithQAComponent,
    FeedbackPageComponent,
    QuestionCategoryComponent,
    QuestionComponent,
    TrainerFeedbackPageComponent,
    FeedbackQuestionComponent,
    StepperComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule
  ],
  providers: [FormTypeService],
  bootstrap: [AppComponent]
})
export class AppModule { }

@NgModule({})
export class CourseFeedbackSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AppModule,
      providers: []
    };
  }
}
