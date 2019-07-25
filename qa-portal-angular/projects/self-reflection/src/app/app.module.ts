import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { TrainerReflectionComponent } from './trainer-reflection/trainer-reflection.component';
import { CommentBoxComponent } from './trainer-reflection/comment-box/comment-box.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainerReflectionComponent,
    CommentBoxComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule
  ],
  providers: [],
  bootstrap: [
    AppComponent
  ]
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
