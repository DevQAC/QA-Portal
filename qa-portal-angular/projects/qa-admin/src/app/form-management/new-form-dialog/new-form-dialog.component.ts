import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { FormService } from '../../_common/services/form.service';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { finalize, take } from 'rxjs/operators';

@Component({
  selector: 'app-new-form-dialog',
  templateUrl: './new-form-dialog.component.html'
})
export class NewFormDialogComponent {

  public formForm: FormGroup;
  public isLoading = false;

  constructor(
    private formService: FormService,
    public dialogRef: MatDialogRef<NewFormDialogComponent>,
    private errorService: QaErrorHandlerService
  ) {
    this.formForm = new FormBuilder().group({
      formName: ['', Validators.required],
      description: ['']
    });
  }

  public onSubmit(): void {
    this.isLoading = true;
    this.formService.addForm(this.formForm.value).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      form => this.dialogRef.close(form),
      err => this.errorService.handleError(err)
    );
  }
}
