import {NgModule} from '@angular/core';
import {FormsModule} from '@angular/forms';

import {AppRoutingModule} from './qa-self-reflection-routing.module';
import {QaCommonModule} from '../../../qa-common/src/app/qa-common.module';
import {SummaryService} from './cohort-summary/services/summary.service';
import {TraineeReflectionComponent} from './trainee-reflection/trainee-reflection.component';
import {SelfReflectionHistoryComponent} from './self-reflection-history/self-reflection-history.component';
import {CohortSummaryComponent} from './cohort-summary/cohort-summary.component';
import {CohortTableComponent} from './cohort-summary/cohort-table/cohort-table.component';
import {MatTableModule, MatPaginatorModule, MatSortModule, MatRippleModule} from '@angular/material';
import {CohortChartComponent} from './cohort-summary/cohort-chart/cohort-chart.component';
import {CohortTraineesComponent} from './cohort-trainees/cohort-trainees.component';
import {CohortTraineesService} from './cohort-trainees/services/cohort-trainees.service';
import {SelfReflectionFormService} from './trainee-reflection/services/self-reflection-form.service';
import {TraineeNewReflectionComponent} from './trainee-new-reflection/trainee-new-reflection.component';
import {ReflectionHistoryService} from './self-reflection-history/services/reflection-history.service';
import {SelfReflectionFormStateService} from './_common/services/self-reflection-form-state.service';
import {TrainerReflectionComponent} from './trainer-reflection/trainer-reflection.component';
import {TrainerReflectionService} from './trainer-reflection/services/trainer-reflection.service';
import {ReflectionQuestionService} from './trainee-new-reflection/services/reflection-question.service';

@NgModule({
  declarations: [
    TrainerReflectionComponent,
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
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatRippleModule
  ],
  providers: [
    TrainerReflectionService,
    SummaryService,
    ReflectionQuestionService,
    CohortTraineesService,
    SelfReflectionFormService,
    ReflectionHistoryService,
    SelfReflectionFormStateService
  ]
})
export class QaSelfReflectionModule {
}
