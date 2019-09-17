import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './qa-self-reflection-routing.module';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { TrainerCohortsService } from '../../../portal-core/src/app/_common/services/trainer-cohorts-service/trainer-cohorts.service';
import { TrainerReflectionComponent } from './trainer-reflection/trainer-reflection.component';
import { CommentBoxComponent } from './trainer-reflection/comment-box/comment-box.component';
import { SaveButtonComponent } from './trainer-reflection/save-button/save-button.component';
import { SummaryService } from './cohort-summary/services/summary.service';
import { TraineeReflectionComponent } from './trainee-reflection/trainee-reflection.component';
import { SelfReflectionHistoryComponent } from './self-reflection-history/self-reflection-history.component';
import { CohortSummaryComponent } from './cohort-summary/cohort-summary.component';
import { CohortTableComponent } from './cohort-table/cohort-table.component';
import { MatTableModule, MatPaginatorModule, MatSortModule, MatRippleModule } from '@angular/material';
import { CohortChartComponent } from './cohort-chart/cohort-chart.component';
import { CohortTraineesComponent } from './cohort-trainees/cohort-trainees.component';
import { HttpClientModule } from '@angular/common/http';
import { SelfReflectionService } from './trainer-reflection/services/self-reflection.service';
import { CohortTraineesService } from './cohort-trainees/services/cohort-trainees.service';
import { RatedQuestionsService } from './trainee-reflection/services/rated-questions.service';
import { SelfReflectionFormService } from './trainee-reflection/services/self-reflection-form.service';
import { TraineeNewReflectionComponent } from './trainee-new-reflection/trainee-new-reflection.component';
import { ReflectionHistoryService } from './self-reflection-history/services/reflection-history.service';
import { SelfReflectionFormStateService } from './_common/services/self-reflection-form-state.service';

@NgModule({
  declarations: [
    TrainerReflectionComponent,
    CommentBoxComponent,
    SaveButtonComponent,
    TraineeReflectionComponent,
    SelfReflectionHistoryComponent,
    CohortSummaryComponent,
    CohortTableComponent,
    CohortChartComponent,
    CohortTraineesComponent,
    TraineeNewReflectionComponent
  ],
  imports: [
    QaCommonModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatRippleModule
  ],
  providers: [
    SummaryService,
    TrainerCohortsService,
    SelfReflectionService,
    CohortTraineesService,
    RatedQuestionsService,
    SelfReflectionFormService,
    ReflectionHistoryService,
    SelfReflectionFormStateService
  ]
})
export class QaSelfReflectionModule { }
