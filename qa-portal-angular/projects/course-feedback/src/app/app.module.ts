import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import { TraineeCourseEvaluationComponent } from './trainee-course-evaluation/trainee-course-evaluation.component';
import { WhyThisCourseQuestionComponent } from './trainee-course-evaluation/why-this-course-question/why-this-course-question.component';
import { WhyWithQAComponent } from './trainee-course-evaluation/why-with-qa/why-with-qa.component';
import { QuestionComponent } from './question/question.component';

@NgModule({
  declarations: [
    AppComponent,
    TraineeCourseEvaluationComponent,
    WhyThisCourseQuestionComponent,
    WhyWithQAComponent,
    QuestionComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule
  ],
  providers: [],
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