import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ICategory } from '../_common/models/form-category.model';
import { IGenericQuestion } from '../_common/models/generic-question.model';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  @Input() value: any;
  @Output() change = new EventEmitter<any>();

  onQuestionResponseChange(event, i) {
    this.value.questionResponses[i] = event;
    this.change.emit(this.value);
  }

  trackQuestion(_index: number, question: IGenericQuestion<any>) {
    return question.id;
  }

}
