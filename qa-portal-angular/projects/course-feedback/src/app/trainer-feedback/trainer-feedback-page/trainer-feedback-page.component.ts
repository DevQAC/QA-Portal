import {Component, OnInit} from '@angular/core';

import {FormTypeService} from '../../_common/services/form-type.service';
import {TRAINER_FEEDBACK_FORM} from '../../_common/models/question-url.constants';
import {QuestionCategoryModel} from '../../_common/models/question-category.model';
import {FormGroup, FormBuilder, Validators} from '@angular/forms';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit {

  dataLoaded = false;

  dataModel: QuestionCategoryModel[];

  constructor(private formTypeService: FormTypeService,) {
  }

  ngOnInit() {
    this.formTypeService.getFormType(TRAINER_FEEDBACK_FORM).subscribe((response: QuestionCategoryModel[]) => {
      this.dataModel = response;
      this.dataLoaded = true;
    });
  }
}
