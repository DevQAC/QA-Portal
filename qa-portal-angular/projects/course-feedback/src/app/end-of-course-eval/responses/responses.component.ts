import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { DataModel } from '../../_common/models/data.model';

@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {

  @Input() value : QuestionModel;
  @Input() question: DataModel;

  constructor() { }

  ngOnInit() {
  }

}
