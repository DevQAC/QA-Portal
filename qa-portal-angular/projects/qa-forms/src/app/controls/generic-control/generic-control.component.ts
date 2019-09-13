import { Component, Input, EventEmitter, Output } from '@angular/core';
import { IQuestion, IQuestionResponse } from '../../_common/models';

export interface IGenericControl {
  question: IQuestion;
  questionResponse: IQuestionResponse;
  questionResponseChange: EventEmitter<IQuestionResponse>;
  isDisabled: boolean;
  displayDirection: string;
}

@Component({
  selector: 'app-generic-control',
  templateUrl: './generic-control.component.html',
  styleUrls: ['./generic-control.component.css']
})
export class GenericControlComponent implements IGenericControl {

  @Input() question: IQuestion;

  @Input() questionResponse: IQuestionResponse;

  @Input() isDisabled: boolean;

  @Input() displayDirection: string;

  @Output() questionResponseChange = new EventEmitter<IQuestionResponse>();

  announceChange(): void {
    // this.questionResponseChange.emit(this.questionResponse);
  }

  setComment(comment: string): void {
    if (!!comment) {
      if (!this.questionResponse.comment) {
        this.questionResponse.comment = {id: null, content: comment};
      } else {
        this.questionResponse.comment.content = comment;
      }
      this.announceChange();
    }

    if (!comment && !!this.questionResponse.comment) {
      this.questionResponse.comment.content = comment;
      this.announceChange();
    }
  }

  setResponseValues(value: string[]): void {
    this.questionResponse.responseValues = value;
    this.announceChange();
  }
}
