import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {
  @Input() value: QuestionModel;
  @Input() selectionType: string;
  @Output() change = new EventEmitter<QuestionModel>();

  onChange(event: QuestionModel): void {
    this.change.emit(event);
  }
}
