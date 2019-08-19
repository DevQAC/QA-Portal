import {Component, OnInit} from '@angular/core';

import {FormTypeService} from '../../_common/services/form-type.service';
import {TRAINER_FEEDBACK_FORM} from '../../_common/models/question-url.constants';
import { ICategory } from 'projects/qa-forms/src/app/_common/models/form-category.model';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit {

  dataLoaded = false;

  dataModel: ICategory[];

  constructor(private formTypeService: FormTypeService,) {
  }

  ngOnInit() {
    this.formTypeService.getFormType(TRAINER_FEEDBACK_FORM).subscribe((response: ICategory[]) => {
      this.dataModel = response;
      // this.initialiseQuestionResponse();
      this.dataLoaded = true;
    });
  }

  saveFeedback() {
    console.log('In save feedback');
    // this.populateQuestionResponseArray();
    // Call service to save feedback form
    console.warn(this.dataModel);

  }

  // /**
  //  * Populates the response property of the QuestionResponseModel which is passed to the rated question common component
  //  * This is a short term workaround due to change of DB schema to handle multiple values in a response (for checkboxes)
  //  */
  // private initialiseQuestionResponse() {
  //   // TODO
  //   // Quick and dirty way to populate the value that needs to be passed to the rated question common component -
  //   // Need this to be changed when full refactor of this area is done
  //   this.dataModel.forEach((category) => {
  //     category.questions.forEach((question) => {
  //       if (!!question.responseValues && question.responseValues.length === 1) {
  //         question.response = question.responseValues[0];
  //       }
  //     });
  //   });
  // }

  /**
   * Populates the response array property of the QuestionResponseModel which is send to the spring boot service. This
   * is part of the short term workaround due to change of DB schema to handle multi value responses
   */
  // private populateQuestionResponseArray() {
  //   this.dataModel.forEach((category) => {
  //     category.questions.forEach((question) => {
  //       if (!!question.response) {
  //         question.responseValues = [];
  //         question.responseValues.push(question.response);
  //       }
  //     });
  //   });
  // }
}
