import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';

@Component({
  selector: 'app-problem-reporter',
  templateUrl: './problem-reporter.component.html',
  styleUrls: ['./problem-reporter.component.css']
})
export class ProblemReporterComponent implements OnInit {

  @Input() question : QuestionModel;
  
  constructor() { }

  ngOnInit() {
  }

}
