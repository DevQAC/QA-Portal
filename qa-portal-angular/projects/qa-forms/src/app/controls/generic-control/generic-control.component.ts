import { Component, Input, EventEmitter, Output } from '@angular/core';
import { IGenericQuestion } from '../../_common/models/generic-question.model';

export interface IGenericControl<ResponseType> {
  question: IGenericQuestion<ResponseType>;
  questionChange: EventEmitter<IGenericQuestion<ResponseType>>;
}

@Component({
  selector: 'app-generic-control',
  templateUrl: './generic-control.component.html',
  styleUrls: ['./generic-control.component.css']
})
export class GenericControlComponent<ResponseType> implements IGenericControl<ResponseType> {

  @Input() question: IGenericQuestion<ResponseType>;
  @Output() questionChange = new EventEmitter<IGenericQuestion<ResponseType>>();

  protected announceChange(): void {
    this.questionChange.emit(this.question);
  }

}
