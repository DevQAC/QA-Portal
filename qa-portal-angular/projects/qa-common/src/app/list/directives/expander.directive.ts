import { Directive, TemplateRef, OnInit, ViewContainerRef } from '@angular/core';
import { ListContext } from '../models/list-context.model';

@Directive({
  selector: '[appListExpander]'
})
export class ExpanderDirective implements OnInit {

  public context: ListContext<any>;

  constructor(
    public template: TemplateRef<any>,
    private viewContainer: ViewContainerRef) { }

  ngOnInit() {
    this.viewContainer.createEmbeddedView(this.template, this.context);
  }
}
