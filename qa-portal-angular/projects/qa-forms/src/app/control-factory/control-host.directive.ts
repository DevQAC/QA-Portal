import { Directive, ViewContainerRef } from '@angular/core';

@Directive({
  selector: '[appControlHost]'
})
export class ControlHostDirective {

  constructor(public viewContainerRef: ViewContainerRef) { }

}
