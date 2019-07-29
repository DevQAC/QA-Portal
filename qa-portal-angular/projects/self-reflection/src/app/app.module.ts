import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { TrainerCohortsComponent } from './trainer-cohorts/trainer-cohorts.component';
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

@NgModule({
  declarations: [
    AppComponent,
    TrainerCohortsComponent,
    TrainerReflectionComponent,
    CommentBoxComponent,
    SaveButtonComponent,
    TraineeReflectionComponent,
    AppComponent,
    SelfReflectionHistoryComponent,
    CohortSummaryComponent,
    CohortTableComponent,
    CohortChartComponent,
    CohortTraineesComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    AppRoutingModule,
    QaCommonModule,
    FormsModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    MatRippleModule
  ],
  providers: [SummaryService,
    TrainerCohortsService,
    SelfReflectionService,
    CohortTraineesService],
  bootstrap: [AppComponent]
})
export class AppModule {
}

@NgModule({})
export class SelfReflectionSharedModule {
  static forRoot(): ModuleWithProviders {
    return {
      ngModule: AppModule,
      providers: []
    };
  }
}
