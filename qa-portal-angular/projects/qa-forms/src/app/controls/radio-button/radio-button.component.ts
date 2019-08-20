import { Component, OnInit } from '@angular/core';
import { GenericControlComponent } from '../generic-control/generic-control.component';

@Component({
  selector: 'app-radio-button',
  templateUrl: './radio-button.component.html',
  styleUrls: ['./radio-button.component.css']
})
export class RadioButtonComponent extends GenericControlComponent<string> implements OnInit  {

  ngOnInit(): void {
    this.questionResponse.responseValues = this.questionResponse.responseValues || '';
    this.questionResponse.comment = {
      ...{content: ''},
      ...this.questionResponse.comment
    };
  }

  setRadioResponse(response: string): void {
    this.questionResponse.responseValues = response;
    this.announceChange();
  }

}
