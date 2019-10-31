import { Component } from '@angular/core';
import { LocationService } from '../../_common/services/location.service';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { take, finalize } from 'rxjs/operators';

@Component({
  selector: 'app-new-location-dialog',
  templateUrl: './new-location-dialog.component.html'
})
export class NewLocationDialogComponent {

  public locationForm: FormGroup;
  public isLoading = false;

  constructor(
    private locationService: LocationService,
    public dialogRef: MatDialogRef<NewLocationDialogComponent>,
    private errorService: QaErrorHandlerService
  ) {
    this.locationForm = new FormBuilder().group({
      name: ['', Validators.required],
      address: ['', Validators.required]
    });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.locationService.addLocation(this.locationForm.value).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      location => this.dialogRef.close(location),
      err => this.errorService.handleError(err)
    );
  }
}
