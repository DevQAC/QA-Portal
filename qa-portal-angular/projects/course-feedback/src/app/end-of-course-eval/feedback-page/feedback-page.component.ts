import {Component, OnInit} from '@angular/core';
import {COURSE_EVAL_FORM} from '../../_common/models/question-url.constants';
import {FormTypeService} from '../../_common/services/form-type.service';
import {QuestionCategoryModel} from '../../_common/models/question-category.model';

@Component({
  selector: 'app-feedback-page',
  templateUrl: './feedback-page.component.html',
  styleUrls: ['./feedback-page.component.css']
})
export class FeedbackPageComponent implements OnInit {

  public formInfo: QuestionCategoryModel[] = [];


  constructor(private formTypeService: FormTypeService) {
  }

  ngOnInit() {
    this.formTypeService.getFormType(COURSE_EVAL_FORM).subscribe((response: QuestionCategoryModel[]) => {
      this.formInfo = response;
    });
  }

  /**
   * This method will submit the current state of the form.
   * @method onFeedbackSubmit
   * @memberof FeedbackPageComponent
   */
  onFeedbackSubmit() {
    this.formTypeService.sendEvalForm(this.formInfo);
  }

  onCategoryChange(event: QuestionCategoryModel, index: number): void {
    this.formInfo[index] = event;
  }
}
