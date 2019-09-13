import {Component, OnInit} from '@angular/core';
import {GenericControlComponent} from '../generic-control/generic-control.component';
import {MatCheckboxChange} from '@angular/material';

@Component({
  selector: 'app-check-box',
  templateUrl: './check-box.component.html',
  styleUrls: ['./check-box.component.css']
})
export class CheckBoxComponent extends GenericControlComponent implements OnInit {

  ngOnInit(): void {
  }

  /**
   *This function is used to extract from the checkboxes whether they are checked or not, if they are checked then their corresponding entry will be added to the evaluation form,
   *when the box is unchecked then it will remove the data from the array. The function will emit the value upon every change.
   *
   * @param {MatCheckboxChange} { checked, source: { value } }
   * @memberof ResponsesComponent
   */
  setCheckResponse({checked, source: {value}}: MatCheckboxChange): void {
    if (checked) {
      this.questionResponse.responseValues.push(value);
    } else {
      this.questionResponse.responseValues = this.questionResponse.responseValues.filter(v => v !== value);
    }
    this.announceChange();
  }

  matchesValue(option: string, responseValues: string[]): boolean {
    return responseValues.includes(option);
  }
}
