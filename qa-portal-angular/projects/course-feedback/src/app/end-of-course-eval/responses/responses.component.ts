import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { COURSE_EVAL_FORM } from '../../_common/models/question_url_constants';
import { FormTypeService } from '../../_common/services/form-type.service';
import { DataModel } from '../../_common/models/data.model';


@Component({
  selector: 'app-responses',
  templateUrl: './responses.component.html',
  styleUrls: ['./responses.component.css']
})
export class ResponsesComponent implements OnInit {

  @Input() value: QuestionModel;
  @Input() selectionType: string;
  @Input() radioButtons: boolean;
  @Input() checkBoxes: boolean;

  constructor(private formTypeService: FormTypeService) {}

  
  ngOnInit() {

  }
}
