import {BrowserModule} from '@angular/platform-browser';
import {ModuleWithProviders, NgModule} from '@angular/core';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import { CohortSummaryComponent } from './cohort-summary/cohort-summary.component';
import {SummaryService} from './cohort-summary/services/summary.service';

@NgModule({
  declarations: [
    AppComponent,
    CohortSummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule
  ],
  providers: [SummaryService],
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
