import { Directive, TemplateRef, ViewContainerRef, OnInit } from '@angular/core';
import { ListContext } from '../models/list-context.model';

@Directive({
  selector: '[appListTitle]'
})
export class TitleDirective implements OnInit {

  public context: ListContext<any>;

  constructor(
    public template: TemplateRef<any>,
    private viewContainer: ViewContainerRef) { }

  ngOnInit() {
    this.viewContainer.createEmbeddedView(this.template, this.context);
  }
}
