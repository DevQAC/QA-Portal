import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { FormComponent } from './form/form.component';
import { CategoryComponent } from './category/category.component';
import { ControlFactoryComponent } from './control-factory/control-factory.component';
import { QaCommonModule } from 'projects/qa-common/src/app/app.module';

@NgModule({
  declarations: [
    FormComponent,
    CategoryComponent,
    ControlFactoryComponent
  ],
  imports: [
    BrowserModule,
    QaCommonModule
  ],
  exports: [
    FormComponent
  ]
})
export class QaFormsModule { }
