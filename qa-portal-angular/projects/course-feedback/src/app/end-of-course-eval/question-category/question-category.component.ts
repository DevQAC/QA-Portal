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
  /**
   * This is used to send the value of each individual catagories back to the database, it is collated further up in feedback-page.component
   * @property change
   * @memberof QuestionCategoryComponent
   */
  @Output() change = new EventEmitter<DataModel>();
  
  onQuestionChange(event: QuestionModel, index: number): void {
    this.value.questions[index] = event;
    this.change.emit(this.value);
  }
}
