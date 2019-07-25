import {BrowserModule} from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import { CohortSummaryComponent } from './cohort-summary/cohort-summary.component';
import { CohortTableComponent } from './cohort-table/cohort-table.component';
import { MatTableModule, MatPaginatorModule, MatSortModule } from '@angular/material';
import { CohortChartComponent } from './cohort-chart/cohort-chart.component';


@NgModule({
  declarations: [
    AppComponent,
    CohortSummaryComponent,
    CohortTableComponent,
    CohortChartComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule
  ],
  providers: [],
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
