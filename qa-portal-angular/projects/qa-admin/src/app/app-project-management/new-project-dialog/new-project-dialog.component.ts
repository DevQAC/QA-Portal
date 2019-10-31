import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApplicationService } from '../../_common/services/application.service';
import { take, finalize } from 'rxjs/operators';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-new-project-dialog',
  templateUrl: './new-project-dialog.component.html',
  styleUrls: ['./new-project-dialog.component.css']
})
export class NewProjectDialogComponent implements OnInit {


  public projectForm: FormGroup;
  public isLoading = false;

  constructor(
    private appService: ApplicationService,
    public dialogRef: MatDialogRef<NewProjectDialogComponent>,
    private errorService: QaErrorHandlerService
  ) { }

  ngOnInit() {
    this.projectForm = new FormBuilder().group({
      name: ['', Validators.required]
    });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.appService.addProject(this.projectForm.value).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      project => this.dialogRef.close(project),
      err => this.errorService.handleError(err)
    );
  }

}
