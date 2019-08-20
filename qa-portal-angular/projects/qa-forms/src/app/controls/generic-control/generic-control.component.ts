import { Component, Input, EventEmitter, Output } from '@angular/core';
import { IGenericQuestion } from '../../_common/models/generic-question.model';

export interface IGenericControl<ResponseType> {
  question: IGenericQuestion<ResponseType>;
  questionChange: EventEmitter<IGenericQuestion<ResponseType>>;
}

export interface IQuestionResponse<ResponseType> {

}

@Component({
  selector: 'app-generic-control',
  templateUrl: './generic-control.component.html',
  styleUrls: ['./generic-control.component.css']
})
export class GenericControlComponent<ResponseType> implements IGenericControl<ResponseType> {

  @Input() question: IGenericQuestion<ResponseType>;

  @Input() questionResponse: IQuestionResponse<ResponseType>;
  @Output() questionResponseChange = new EventEmitter<IQuestionResponse<ResponseType>>();

  protected announceChange(): void {
    this.questionResponseChange.emit(this.questionResponse);
  }

}
