import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { MatRadioChange } from '@angular/material';

@Component({
  selector: 'app-problem-reporter',
  templateUrl: './problem-reporter.component.html',
  styleUrls: ['./problem-reporter.component.css']
})
export class ProblemReporterComponent implements OnInit {

  @Input() question: QuestionModel;

  @Output() change: EventEmitter<MatRadioChange>;

  selectedOption: string;

  constructor() { }

  ngOnInit() {

  }

}
