import {Component, Input, Output, EventEmitter} from '@angular/core';
import {QuestionCategoryModel} from '../../_common/models/question-category.model';
import {ReturnedQuestionModel} from '../../_common/models/returned-question.model';

@Component({
  selector: 'app-question-category',
  templateUrl: './question-category.component.html',
  styleUrls: ['./question-category.component.css']
})
export class QuestionCategoryComponent {
  @Input() value: QuestionCategoryModel;
  /**
   * This is used to send the value of each individual catagories back to the database, it is collated further up in feedback-page.component
   * @property change
   * @memberof QuestionCategoryComponent
   */
  @Output() change = new EventEmitter<QuestionCategoryModel>();

  onQuestionChange(event: ReturnedQuestionModel, index: number): void {
    this.value.questions[index] = event;
    this.change.emit(this.value);
  }
}
