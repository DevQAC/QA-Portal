import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent {
  @Input() value: QuestionModel;
  /**
   * This is used to define what type of reponse is required from the user. Currently it is set to take either Radio buttons or check boxes
   * @property selectionType
   * @memberof QuestionComponent
   */
  @Input() selectionType: string;
  /**
   * This is used to send the value of each individual question back to the database, it is collated further up in feedback-page.component
   * @property change
   * @memberof QuestionComponent
   */
  @Output() change = new EventEmitter<QuestionModel>();

  onChange(event: QuestionModel): void {
    this.change.emit(event);
  }
}
