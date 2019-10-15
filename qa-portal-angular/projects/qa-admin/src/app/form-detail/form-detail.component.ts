import { Component, OnInit } from '@angular/core';
import { FormService } from '../_common/services/form.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { FormModel } from 'projects/portal-core/src/app/_common/models/form.model';
import { finalize } from 'rxjs/operators';
import { QuestionCategoryModel } from 'projects/portal-core/src/app/_common/models/question-category.model';
import { QuestionModel } from 'projects/portal-core/src/app/_common/models/question.model';
import { MatChipInputEvent } from '@angular/material';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';

@Component({
  selector: 'app-form-detail',
  templateUrl: './form-detail.component.html'
})
export class FormDetailComponent implements OnInit {
  public formForm: FormGroup;
  public isLoading = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  public form: FormModel;

  public selectionTypes = [
    { value: 'RADIO_BUTTON', label: 'Single' },
    { value: 'CHECK_BOX', label: 'Multiple' }
  ];

  constructor(
    private formService: FormService,
    private errorService: QaErrorHandlerService,
    private aR: ActivatedRoute,
    private toastr: QaToastrService
  ) {
    this.formForm = new FormBuilder().group({
      formName: ['', Validators.required],
      description: ['']
    });
  }

  ngOnInit() {
    this.formService.getFormById(this.aR.snapshot.params.id)
      .pipe(finalize(() => {
        this.formForm.enable();
        this.isLoading = false;
      }))
      .subscribe(
        form => {
          this.form = form;
          this.formForm.patchValue({
            ...this.form
          });
        }, err => this.errorService.handleError(err)
      );
  }

  public onAddCategoryClicked(): void {
    this.form.questionCategories.push(new QuestionCategoryModel());
  }

  public onAddQuestionClicked(category: QuestionCategoryModel): void {
    category.questions.push(new QuestionModel());
  }

  addOption(event: MatChipInputEvent, question: QuestionModel): void {
    const input = event.input;
    const value = event.value;

    if ((value || '').trim()) {
      question.selectionOptionsList.push(value);
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  removeOption(option: string, question: QuestionModel): void {
    const index = question.selectionOptionsList.indexOf(option);
    if (index >= 0) {
      question.selectionOptionsList.splice(index, 1);
    }
  }

  public onSaveFormClicked() {
    const form = {
      ...this.form,
      ...this.formForm.value
    };

    this.formService.saveForm(form).subscribe(resp => {
      this.toastr.showSuccess('Form updated');
    },
      err => this.errorService.handleError(err));
  }
}
