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

  setCheckResponse({ checked, source: { value } }: MatCheckboxChange): void {
    if (checked) {
      this.value.selectionOptions_PLACEHOLDER.push(value);
    } else {
      this.value.selectionOptions_PLACEHOLDER.filter(v => v !== value);
    }
    this.change.emit(this.value);
  }
}
