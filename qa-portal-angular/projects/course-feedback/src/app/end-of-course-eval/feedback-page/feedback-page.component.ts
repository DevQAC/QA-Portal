import { Component, OnInit } from '@angular/core';
import { COURSE_EVAL_FORM } from '../../_common/Models/question_url_constants';
import { FormTypeService } from '../../_common/Services/form-type.service';
import { DataModel } from '../../_common/Models/data.model';
import { DataModelArray } from '../../_common/Models/data.model.array';

@Component({
  selector: 'app-feedback-page',
  templateUrl: './feedback-page.component.html',
  styleUrls: ['./feedback-page.component.css']
})
export class FeedbackPageComponent implements OnInit {

  formInfo : DataModel;

  constructor(private formTypeService: FormTypeService) { }

    ngOnInit() {
      this.formTypeService.getFormType(COURSE_EVAL_FORM).subscribe((information) => {
        this.formInfo = information;
        console.log(this.formInfo);
    });
  }
}
