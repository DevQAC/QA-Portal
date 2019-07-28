import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { FormsModule } from '@angular/forms';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {QaCommonModule} from '../../../qa-common/src/app/app.module';
import { TrainerCohortsComponent } from './trainer-cohorts/trainer-cohorts.component';
import {TrainerCohortsService} from '../../../portal-core/src/app/_common/services/trainer-cohorts-service/trainer-cohorts.service';
import { TrainerReflectionComponent } from './trainer-reflection/trainer-reflection.component';
import { CommentBoxComponent } from './trainer-reflection/comment-box/comment-box.component';
import { SaveButtonComponent } from './trainer-reflection/save-button/save-button.component';
import { CohortSummaryComponent } from './cohort-summary/cohort-summary.component';
import {SummaryService} from './cohort-summary/services/summary.service';


@NgModule({
  declarations: [
    AppComponent,
    TrainerCohortsComponent,
    TrainerReflectionComponent,
    CommentBoxComponent,
    SaveButtonComponent,
    CohortSummaryComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    FormsModule
  ],
  providers: [SummaryService,
    TrainerCohortsService],
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
