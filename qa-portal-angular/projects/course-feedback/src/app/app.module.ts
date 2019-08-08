import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import { TrainerEvaluationHistoryComponent } from './trainer-evaluation-history/trainer-evaluation-history.component';
import { HttpClientModule } from '@angular/common/http';
import { RetrieveTrainerEvaluationHistoryService } from './trainer-evaluation-history/services/retrieve-trainer-evaluation-history.service';
import { InstructorZoneTitleComponent } from './trainer-evaluation-history/instructor-zone-title/instructor-zone-title.component';
import { SearchFormComponent } from './trainer-evaluation-history/search-form/search-form.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainerEvaluationHistoryComponent,
    InstructorZoneTitleComponent,
    SearchFormComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    HttpClientModule
  ],
  providers: [
    RetrieveTrainerEvaluationHistoryService,
    SearchFormComponent
  ],
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