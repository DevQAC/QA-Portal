import { Component, OnInit, Input } from '@angular/core';
import {evaluationQuestions} from '../models/questions';
import { FormControl, FormGroup } from '@angular/forms';
import { SelectedOptionModel } from './why-with-qa.model';


@Component({
  selector: 'app-why-with-qa',
  templateUrl: './why-with-qa.component.html',
  styleUrls: ['./why-with-qa.component.css']
})
export class WhyWithQAComponent implements OnInit {
  @Input() answer : string;
  @Input() selectedOption: SelectedOptionModel
  
  constructor() { }

  ngOnInit() {
  }
  
  questionText: string = "Why did you choose to book your training with QA?"
  evaluationQuestions = new evaluationQuestions();
  question: string[] = this.evaluationQuestions.questionWhyQA;
  setResponse(answer: string){
    this.selectedOption.response = answer;
  }
  
}
