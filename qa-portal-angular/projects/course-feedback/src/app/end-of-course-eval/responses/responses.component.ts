import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { CheckBoxResponseModel } from '../../_common/models/checkbox-response.model'
import { SelectedRatingModel } from 'projects/qa-common/src/app/rated-question/selected-rating.model';



@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {

  
  @Input() value: QuestionModel;
  @Input() selectionType: String;
  @Input() radioButtons: Boolean;
  @Input() selectedRating: SelectedRatingModel;
  @Input() selectedResponse: CheckBoxResponseModel;
  @Input() checkBoxes: Boolean;
  @Input() userResponse : String;

  constructor() {}

  setRadioResponse(response: string) {
    console.log(response)
  }

  setCheckResponse(response: string) {
    console.log(response)
    // this.selectedResponse.response.push(response);
    console.log(this.selectedResponse.response.push(response))
  }
  ngOnInit() {

  }
}
