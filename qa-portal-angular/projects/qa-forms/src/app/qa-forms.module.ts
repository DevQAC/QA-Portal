import { NgModule } from '@angular/core';

import { FormComponent } from './form/form.component';
import { CategoryComponent } from './category/category.component';
import { ControlFactoryComponent } from './control-factory/control-factory.component';
import { QaCommonModule } from 'projects/qa-common/src/app/qa-common.module';
import { ControlHostDirective } from './control-factory/control-host.directive';
import { CONTROLS } from './_common/models/control.constants';
import { GenericControlComponent } from './controls/generic-control/generic-control.component';

@NgModule({
  declarations: [
    FormComponent,
    CategoryComponent,
    ControlFactoryComponent,
    ControlHostDirective,
    ...CONTROLS,
    GenericControlComponent
  ],
  imports: [
    QaCommonModule
  ],
  exports: [
    FormComponent,
    CategoryComponent
  ],
  entryComponents: [
    ...CONTROLS
  ]
})
export class QaFormsModule { }
