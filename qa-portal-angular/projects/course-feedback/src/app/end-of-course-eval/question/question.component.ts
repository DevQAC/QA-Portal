import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { DataModel } from '../../_common/models/data.model';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})
export class QuestionComponent implements OnInit {


  @Input() value : QuestionModel;
  @Input() selectionType : String;

  constructor() { }

  ngOnInit() {
  }

}
