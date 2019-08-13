import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { CourseEvaluationComponent } from './course-evaluation/course-evaluation.component';
import { EvaluationTableComponent } from './evaluation-table/evaluation-table.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { FormTypeService } from './_common/services/form-type.service';
import { FeedbackPageComponent } from './end-of-course-eval/feedback-page/feedback-page.component';
import { QuestionCategoryComponent } from './end-of-course-eval/question-category/question-category.component';
import { QuestionComponent } from './end-of-course-eval/question/question.component';
import { ResponsesComponent } from './end-of-course-eval/responses/responses.component';
import { SaveButtonComponent } from './end-of-course-eval/save-button/save-button.component';

@NgModule({
  declarations: [
    AppComponent,
    CourseEvaluationComponent,
    EvaluationTableComponent,
    FeedbackPageComponent,
    QuestionCategoryComponent,
    QuestionComponent,
    ResponsesComponent,
    SaveButtonComponent
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
