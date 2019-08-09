import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation/trainee-course-evaluation.component';
import { WhyThisCourseQuestionComponent } from './trainee-course-evaluation/why-this-course-question/why-this-course-question.component';
import { WhyWithQAComponent } from './trainee-course-evaluation/why-with-qa/why-with-qa.component';
import { FormTypeService } from './_common/Services/form-type.service';
import { FeedbackPageComponent } from './end-of-course-eval/feedback-page/feedback-page.component';
import { QuestionCategoryComponent } from './end-of-course-eval/question-category/question-category.component';

@NgModule({
  declarations: [
    AppComponent,
    TraineeCourseEvaluationComponent,
    WhyThisCourseQuestionComponent,
    WhyWithQAComponent,
    FeedbackPageComponent,
    QuestionCategoryComponent
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