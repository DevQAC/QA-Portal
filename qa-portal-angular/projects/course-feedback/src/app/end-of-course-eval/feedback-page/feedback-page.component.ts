import { Component, OnInit } from '@angular/core';
import { COURSE_EVAL_FORM } from '../../_common/models/question-url.constants';
import { FormTypeService } from '../../_common/services/form-type.service';
import { ICategory } from 'projects/qa-forms/src/app/_common/models/form-category.model';
import { take } from 'rxjs/operators';

@Component({
  selector: 'app-feedback-page',
  templateUrl: './feedback-page.component.html',
  styleUrls: ['./feedback-page.component.css']
})
export class FeedbackPageComponent implements OnInit {
  public formModel: ICategory[] = [];

  constructor(private formTypeService: FormTypeService) { }

  ngOnInit() {
    this.formTypeService.getFormType(COURSE_EVAL_FORM).subscribe(form => this.formModel = form);
  }


  /**
   * This method will submit the current state of the form.
   * @method onFeedbackSubmit
   * @memberof FeedbackPageComponent
   */
  onFeedbackSubmit() {
    this.formTypeService.sendEvalForm(this.formModel).pipe(take(1)).subscribe((...args) => console.log('OH BOY IT BROKE AGAIN', args));
  }
}
