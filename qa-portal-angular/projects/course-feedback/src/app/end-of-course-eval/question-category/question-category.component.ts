import { Component, Input, Output, EventEmitter } from '@angular/core';
import { DataModel } from '../../_common/models/data.model';
import { QuestionModel } from '../../_common/models/question.model';

@Component({
  selector: 'app-question-category',
  templateUrl: './question-category.component.html',
  styleUrls: ['./question-category.component.css']
})
export class QuestionCategoryComponent {
  @Input() value: DataModel;
  @Output() change = new EventEmitter<DataModel>();
  
  onQuestionChange(event: QuestionModel, index: number): void {
    this.value.questions[index] = event;
    this.change.emit(this.value);
  }
}
