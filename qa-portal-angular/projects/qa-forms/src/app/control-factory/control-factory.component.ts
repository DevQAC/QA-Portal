import {Component, OnInit, Input, ViewChild, ComponentFactoryResolver, EventEmitter, Output, OnDestroy} from '@angular/core';
import {ControlHostDirective} from './control-host.directive';
import {CONTROLS_MAP} from '../_common/models/control.constants';
import {ControlTypes} from '../../../../portal-core/src/app/_common/types/control.types';
import {takeWhile} from 'rxjs/operators';
import {IQuestionResponse, IQuestion} from '../_common/models';
import {IGenericControl} from '../controls/generic-control/generic-control.component';

@Component({
  selector: 'app-control-factory',
  templateUrl: './control-factory.component.html',
  styleUrls: ['./control-factory.component.css']
})
export class ControlFactoryComponent implements OnInit, OnDestroy {
  @ViewChild(ControlHostDirective, {static: true}) controlHost: ControlHostDirective;

  @Input() question: IQuestion;

  @Input() isDisabled: boolean;

  @Input() type: ControlTypes;

  @Input() displayDirection: string;

  @Input() questionResponse: IQuestionResponse;

  @Output() questionResponseChange = new EventEmitter<IQuestionResponse>();

  private keepAlive = true;

  constructor(private componentFactoryResolver: ComponentFactoryResolver) {
  }

  ngOnInit(): void {
    this.loadControl();
  }

  ngOnDestroy(): void {
    this.keepAlive = false;
  }

  private loadControl(): void {
    // console.debug('ControlFactoryComponent::loadControl - question:', this.question, 'type:', this.type, 'questionResponse:', this.questionResponse);
    // Setup factory with correct control
    const componentFactory = this.componentFactoryResolver.resolveComponentFactory(CONTROLS_MAP[this.question.selectionType || this.type]);
    const viewContainerRef = this.controlHost.viewContainerRef;

    // Create control
    viewContainerRef.clear();
    const componentRef = viewContainerRef.createComponent(componentFactory);

    // Hook all the I/O together
    (componentRef.instance as IGenericControl).question = this.question;
    (componentRef.instance as IGenericControl).isDisabled = this.isDisabled;
    (componentRef.instance as IGenericControl).questionResponse = this.questionResponse;
    (componentRef.instance as IGenericControl).displayDirection = this.displayDirection;
    (componentRef.instance as IGenericControl).questionResponseChange
      .pipe(takeWhile(() => this.keepAlive))
      .subscribe(event => {
        // this.questionResponseChange.emit(event);
      });
  }
}
