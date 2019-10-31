import { Component } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApplicationService } from '../../_common/services/application.service';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { finalize, take } from 'rxjs/operators';

@Component({
  selector: 'app-new-app-dialog',
  templateUrl: './new-app-dialog.component.html',
  styleUrls: ['./new-app-dialog.component.css']
})
export class NewAppDialogComponent {

  public appForm: FormGroup;
  public isLoading = false;

  constructor(
    private appService: ApplicationService,
    public dialogRef: MatDialogRef<NewAppDialogComponent>,
    private errorService: QaErrorHandlerService
  ) {
    this.appForm = new FormBuilder().group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      displayOrder: [1, Validators.required],
      baseUrl: ['', Validators.required]
    });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.appService.addApplication(this.appForm.value).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      app => this.dialogRef.close(app),
      err => this.errorService.handleError(err)
    );
  }

}
