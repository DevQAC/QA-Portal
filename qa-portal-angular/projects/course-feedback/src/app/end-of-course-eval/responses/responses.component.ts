import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { SelectedRatingModel } from 'projects/qa-common/src/app/rated-question/selected-rating.model';
import { CheckBoxResponseModel } from '../../_common/models/checkbox-response.model';
import { MatCheckboxChange } from '@angular/material';

interface IQuestionResponse {
  value: string | string[]
}

@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {
  @Input() value: QuestionModel;
  @Input() selectionType: string;
  @Input() selectedRating: SelectedRatingModel;
  @Input() selectedResponse: CheckBoxResponseModel;

  @Output() change = new EventEmitter<IQuestionResponse>();

  ngOnInit(): void {
    this.selectedResponse = {
      responseCheck: [],
      ...this.selectedResponse
    };
  }

  setRadioResponse(response: string): void {
    this.selectedRating.response = response;
  }

  setCheckResponse({ checked, source: { value } }: MatCheckboxChange): void {
    if (checked) {
      this.selectedResponse.responseCheck.push(value);
    } else {
      this.selectedResponse.responseCheck = this.selectedResponse.responseCheck.filter(v => v !== value);
    }

    this.change.emit(null);
  }

}
