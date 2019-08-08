import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import { TrainerEvaluationSummaryComponent } from './trainer-evaluation-summary/trainer-evaluation-summary.component';
import { TableComponentComponent } from './trainer-evaluation-summary/table-component/table-component.component';
import { CourseInfoComponent } from './trainer-evaluation-summary/course-info/course-info.component';
import { InstructorZoneTitleComponent } from './trainer-evaluation-summary/instructor-zone-title/instructor-zone-title.component';
import { SearchBoxComponent } from './trainer-evaluation-summary/search-box/search-box.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainerEvaluationSummaryComponent,
    TableComponentComponent,
    CourseInfoComponent,
    InstructorZoneTitleComponent,
    SearchBoxComponent
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