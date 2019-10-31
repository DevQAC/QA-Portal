import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { TechnologyService } from '../../_common/technology.service';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { take, finalize } from 'rxjs/operators';

@Component({
  selector: 'app-new-category-dialog',
  templateUrl: './new-category-dialog.component.html',
  styleUrls: ['./new-category-dialog.component.css']
})
export class NewCategoryDialogComponent implements OnInit {

  public catForm: FormGroup;
  public isLoading = true;

  constructor(
    private techService: TechnologyService,
    public dialogRef: MatDialogRef<NewCategoryDialogComponent>,
    private errorService: QaErrorHandlerService
  ) {
    this.catForm = new FormBuilder().group({
      categoryName: ['', [Validators.required]]
    });
  }

  ngOnInit() {
    this.isLoading = false;
  }

  public onSubmit(): void {
    this.isLoading = true;
    const newCat = {
      ...this.catForm.value
    };
    this.techService.addCategory(newCat)
      .pipe(
        take(1),
        finalize(() => this.isLoading = false)
      ).subscribe(
        cat => this.dialogRef.close(cat),
        err => this.errorService.handleError(err)
      );
  }

}
