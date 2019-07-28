import { BrowserModule } from '@angular/platform-browser';
import { NgModule, ModuleWithProviders } from '@angular/core';
import { FormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { QaCommonModule } from '../../../qa-common/src/app/app.module';
import { TrainerReflectionComponent } from './trainer-reflection/trainer-reflection.component';
import { CommentBoxComponent } from './trainer-reflection/comment-box/comment-box.component';
import { SaveButtonComponent } from './trainer-reflection/save-button/save-button.component';

@NgModule({
  declarations: [
    AppComponent,
    TrainerReflectionComponent,
    CommentBoxComponent,
    SaveButtonComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    QaCommonModule,
    FormsModule
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
