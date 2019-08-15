import { Component, OnInit } from '@angular/core';

import { FormTypeService } from '../../_common/services/form-type.service';
import { TRAINER_FEEDBACK_FORM } from '../../_common/models/question_url_constants';
import { DataModel } from '../../_common/models/data.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit {

  formInfo: DataModel[] = [];
  firstFormGroup : FormGroup;
  secondFormGroup : FormGroup;
  thirdFormGroup : FormGroup;
  fourthFormGroup : FormGroup;
  fifthFormGroup : FormGroup;

  constructor(private formTypeService: FormTypeService, private _formBuilder : FormBuilder) { }

  ngOnInit() {
    this.formTypeService.getFormType(TRAINER_FEEDBACK_FORM).subscribe((response: DataModel[]) => {
      this.formInfo = response;
    });
    this.firstFormGroup = this._formBuilder.group({ });
    this.secondFormGroup = this._formBuilder.group({ });
    this.thirdFormGroup = this._formBuilder.group({ });
    this.fourthFormGroup = this._formBuilder.group({ });
    this.fifthFormGroup = this._formBuilder.group({ });
  }
}
