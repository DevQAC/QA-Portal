import {Component, OnInit} from '@angular/core';

import {FormTypeService} from '../../_common/services/form-type.service';
import {TRAINER_FEEDBACK_FORM} from '../../_common/models/question_url_constants';
import {DataModel} from '../../_common/models/data.model';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit {

  dataLoaded = false;
  dataModel: any[];


  constructor(private formTypeService: FormTypeService,) {
  }

  ngOnInit() {
    this.formTypeService.getFormType(TRAINER_FEEDBACK_FORM).subscribe((response: DataModel[]) => {
      this.dataModel = response;
      console.log(response);
      console.log("dataModel = " + this.dataModel);
      this.dataLoaded = true;
    });
  }
}
