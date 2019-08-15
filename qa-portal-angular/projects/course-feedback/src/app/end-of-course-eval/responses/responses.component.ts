import { Component, OnInit, Input, EventEmitter, Output } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { MatCheckboxChange } from '@angular/material';


@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {
  @Input() value: QuestionModel;
  @Input() selectionType: 'CHECK_BOX' | 'RADIO_BUTTON';
  /**
   * This is used to send the value of each individual response per question back to the database, it is collated further up in feedback-page.component
   * @property change
   * @memberof ResponsesComponent
   */
  @Output() change = new EventEmitter<QuestionModel>();


  private _defaultOptsMap = {
    'CHECK_BOX': [],
    'RADIO_BUTTON': ''
  };

  ngOnInit(): void {
    this.value = {
      selectionOptions_PLACEHOLDER: this._defaultOptsMap[this.selectionType] || '',
      ...this.value
    };
  }

  setRadioResponse(response: string): void {
    this.value.selectionOptions_PLACEHOLDER = response;
    this.change.emit(this.value);
  }
  /**
   *This function is used to extract from the checkboxes whether they are checked or not, if they are checked then their corresponding entry will be added to the evaluation form,
   *when the box is unchecked then it will remove the data from the array. The function will emit the value upon every change.
   *
   * @param {MatCheckboxChange} { checked, source: { value } }
   * @memberof ResponsesComponent
   */
  setCheckResponse({ checked, source: { value } }: MatCheckboxChange): void {
    if (checked) {
      this.value.selectionOptions_PLACEHOLDER.push(value);
    } else {
      this.value.selectionOptions_PLACEHOLDER.filter(v => v !== value);
    }
    this.change.emit(this.value);
  }
}
