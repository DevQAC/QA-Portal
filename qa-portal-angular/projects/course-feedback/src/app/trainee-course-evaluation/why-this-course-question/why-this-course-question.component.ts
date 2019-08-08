import { Component, OnInit, Input } from '@angular/core';
import { evaluationQuestions } from '../models/questions'
import { FormControl, FormGroup } from '@angular/forms';
import { SelectedOptionModel } from './why-this-course.model';

@Component({
  selector: 'app-why-this-course-question',
  templateUrl: './why-this-course-question.component.html',
  styleUrls: ['./why-this-course-question.component.css']
})
export class WhyThisCourseQuestionComponent implements OnInit {
  @Input() options: string;
  @Input() selectedOption:SelectedOptionModel;
  
  ngOnInit() {
  }
  constructor() { }
  questionText: string = "What was the main reason for attending your course?";
  evaluationQuestions = new evaluationQuestions();
  whyCourseResponse: string;
  question: string[] = this.evaluationQuestions.questionsWhyCourse;
  
  setResponse(options :string) {
    this.selectedOption.response = options
  }
}
