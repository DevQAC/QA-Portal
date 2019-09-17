import { NgModule } from '@angular/core';

import { QaErrorRoutingModule } from './qa-error-routing.module';
import { AppComponent } from './app.component';
import { QaErrorPageComponent } from './qa-error-page/qa-error-page.component';
import { QaCommonModule } from 'projects/qa-common/src/app/qa-common.module';

@NgModule({
  declarations: [
    AppComponent,
    QaErrorPageComponent
  ],
  imports: [
    QaCommonModule,
    QaErrorRoutingModule
  ],
  providers: []
})
export class QaErrorModule { }


