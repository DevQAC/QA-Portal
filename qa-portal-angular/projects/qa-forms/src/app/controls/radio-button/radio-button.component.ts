import { Component, OnInit } from '@angular/core';
import { GenericControlComponent } from '../generic-control/generic-control.component';

@Component({
  selector: 'app-radio-button',
  templateUrl: './radio-button.component.html',
  styleUrls: ['./radio-button.component.css']
})
export class RadioButtonComponent extends GenericControlComponent implements OnInit  {

  ngOnInit(): void {
  }

  setRadioResponse(response: string): void {
    this.questionResponse.responseValues = [response];
    this.announceChange();
  }

  matchesValue(value: string) {
    return !!this.questionResponse.responseValues && (this.questionResponse.responseValues[0] === value);
  }
}
