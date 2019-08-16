import { Component, Input, Output, EventEmitter } from '@angular/core';
import { ICategory } from '../_common/models/form-category.model';
import { IGenericQuestion } from '../_common/models/generic-question.model';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent {
  @Input() value: ICategory;
  @Output() change = new EventEmitter<ICategory>();

  onQuestionChange(event: IGenericQuestion<any>, index: number): void {
    this.value.questions[index] = event;
    this.change.emit(this.value);
  }

}