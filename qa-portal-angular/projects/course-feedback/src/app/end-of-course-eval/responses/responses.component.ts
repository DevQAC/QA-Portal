import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';



@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {

  @Input() value: QuestionModel;
  @Input() selectionType: String;
  @Input() radioButtons: Boolean;
  @Input() checkBoxes: Boolean;

  constructor() {}

  setResponse(response: string) {
    console.log(response)
  }
  ngOnInit() {

  }
}
