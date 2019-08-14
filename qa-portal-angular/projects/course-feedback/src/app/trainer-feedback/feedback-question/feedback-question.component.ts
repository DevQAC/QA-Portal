import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { FormGroup, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-feedback-question',
  templateUrl: './feedback-question.component.html',
  styleUrls: ['./feedback-question.component.css']
})
export class FeedbackQuestionComponent implements OnInit {

  @Input() value : QuestionModel;


  constructor() { }

  ngOnInit() { }
  
}
