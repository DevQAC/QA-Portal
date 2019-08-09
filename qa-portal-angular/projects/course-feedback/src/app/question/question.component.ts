import { Component, OnInit } from '@angular/core';
import { FormTypeService } from '../_common/Services/form-type.service';
import { COURSE_EVAL_FORM } from '../_common/Models/question_url_constants';

@Component({
  selector: 'app-question',
  templateUrl: './question.component.html',
  styleUrls: ['./question.component.css']
})

export class QuestionComponent implements OnInit {
private formQuestions;

  constructor(private formTypeService : FormTypeService) { }

  ngOnInit() {
    this.formTypeService.getFormType(COURSE_EVAL_FORM).subscribe((information) => {
      console.log(information);
      this.formQuestions = information;
    })
  }

}
