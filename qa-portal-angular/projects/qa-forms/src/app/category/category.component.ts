import { Component, Input, Output, EventEmitter, OnInit } from '@angular/core';
import { IQuestion, ICategoryResponse } from '../_common/models';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {
  @Input() categoryResponse: ICategoryResponse;

  @Input() isDisabled: boolean

  @Output() categoryResponseChange = new EventEmitter<ICategoryResponse>();

  ngOnInit(): void {
  }

  onQuestionResponseChange(event, i) {
    this.categoryResponse.questionResponses[i] = event;
    // this.categoryResponseChange.emit(this.categoryResponse);
  }

  trackQuestion(_index: number, question: IQuestion) {
    return question.id;
  }
}
