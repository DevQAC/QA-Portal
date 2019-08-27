import { Component, Input, EventEmitter, Output } from '@angular/core';
import { IQuestion, IQuestionResponse } from '../../_common/models';
export interface IGenericControl<ResponseType = any> {
  question: IQuestion;
  questionResponse: IQuestionResponse<ResponseType>;
  questionResponseChange: EventEmitter<IQuestionResponse<ResponseType>>
}

@Component({
  selector: 'app-generic-control',
  templateUrl: './generic-control.component.html',
  styleUrls: ['./generic-control.component.css']
})
export class GenericControlComponent<ResponseType> implements IGenericControl<ResponseType> {

  @Input() question: IQuestion;

  @Input() questionResponse: IQuestionResponse<ResponseType>;

  @Output() questionResponseChange = new EventEmitter<IQuestionResponse<ResponseType>>();

  announceChange(): void {
    this.questionResponseChange.emit(this.questionResponse);
  }

  setComment(comment: string): void {
    if (!!comment) {
      if (!this.questionResponse.comment) {
        this.questionResponse.comment = {id: null, content: comment};
      }
      this.questionResponse.comment.content = comment;
      this.announceChange();
    }

    if (!comment && !!this.questionResponse.comment) {
      this.questionResponse.comment.content = comment;
      this.announceChange();
    }
  }

  setResponseValues(value: ResponseType): void {
    this.questionResponse.responseValues = value;
    this.announceChange();
  }

}
