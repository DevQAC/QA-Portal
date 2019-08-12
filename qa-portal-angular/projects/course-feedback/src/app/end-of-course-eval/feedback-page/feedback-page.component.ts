import { Component, OnInit } from '@angular/core';
import { COURSE_EVAL_FORM } from '../../_common/models/question_url_constants';
import { FormTypeService } from '../../_common/services/form-type.service';
import { DataModel } from '../../_common/models/data.model';

@Component({
  selector: 'app-feedback-page',
  templateUrl: './feedback-page.component.html',
  styleUrls: ['./feedback-page.component.css']
})
export class FeedbackPageComponent implements OnInit {

  formInfo: DataModel[] = [];

  constructor(private formTypeService: FormTypeService) { }

    ngOnInit() {
      this.formTypeService.getFormType(COURSE_EVAL_FORM).subscribe((response: DataModel[]) => {
        this.formInfo = response;
    });
  }
}
