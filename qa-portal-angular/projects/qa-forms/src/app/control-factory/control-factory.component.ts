import { Component, OnInit, Input, ViewChild, ComponentFactoryResolver, EventEmitter, Output, OnDestroy } from '@angular/core';
import { IGenericQuestion } from '../_common/models/generic-question.model';
import { ControlHostDirective } from './control-host.directive';
import { CONTROLS_MAP } from '../_common/models/control.constants';
import { ControlTypes } from '../_common/types/control.types';
import { takeWhile } from 'rxjs/operators';
import {IGenericControl, IQuestionResponse} from '../controls/generic-control/generic-control.component';

@Component({
  selector: 'app-control-factory',
  templateUrl: './control-factory.component.html',
  styleUrls: ['./control-factory.component.css']
})
export class ControlFactoryComponent implements OnInit, OnDestroy {
  @ViewChild(ControlHostDirective, { static: true }) controlHost: ControlHostDirective;
  @Input() question: IGenericQuestion<any>;
  @Input() type: ControlTypes;
  @Output() questionChange = new EventEmitter<IGenericQuestion<any>>();

  @Input() questionResponse: IQuestionResponse<any>;
  @Output() questionResponseChange = new EventEmitter<IQuestionResponse<any>>();
  private keepAlive = true;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) { }

  ngOnInit(): void {
    this.loadControl();
  }

  ngOnDestroy(): void {
    this.keepAlive = false;
  }

  private loadControl(): void {
    console.debug('ControlFactoryComponent::loadControl - question:', this.question, 'type:', this.type, 'questionResponse:', this.questionResponse);
    // Setup factory with correct control
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(CONTROLS_MAP[this.question.selectionType || this.type]);
    const viewContainerRef = this.controlHost.viewContainerRef;

    // Create control
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);

    // Hook all the I/O together
    (componentRef.instance as IGenericControl<any>).question = this.question;

    (componentRef.instance as IGenericControl<any>).questionResponse = this.questionResponse;
    (componentRef.instance as IGenericControl<any>).questionResponseChange
      .pipe(takeWhile(() => this.keepAlive))
      .subscribe(event => {
        this.questionResponseChange.emit(event);
      });
  }
}
