import { Component, OnInit } from '@angular/core';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';
import { LocationService } from '../_common/services/location.service';
import { finalize } from 'rxjs/operators';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';

@Component({
  selector: 'app-location-detail',
  templateUrl: './location-detail.component.html'
})
export class LocationDetailComponent implements OnInit {

  public locationForm: FormGroup;

  public location: LocationModel;

  public isLoading = true;

  constructor(
    private locationService: LocationService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private toastr: QaToastrService
  ) {
    this.locationForm = new FormBuilder().group({
      name: ['', Validators.required],
      address: ['', [Validators.required]]
    });
    this.locationForm.disable();

  }

  ngOnInit() {
    this.locationService.getLocationById(this.aR.snapshot.params.id)
      .pipe(finalize(() => {
        this.locationForm.enable();
        this.isLoading = false;
      }))
      .subscribe(
        location => {
          this.location = location;
          this.locationForm.patchValue(this.location);
        }, err => this.errorService.handleError(err)
      );
  }

  public onSaveLocationClicked() {
    this.location = {
      ...this.location,
      ...this.locationForm.value,
    };

    this.locationService.saveLocation(this.location)
      .pipe(finalize(() => {
        this.locationForm.enable();
        this.isLoading = false;
      })).subscribe(() => {
        this.toastr.showSuccess('Location updated');
      }, err => this.errorService.handleError(err));
  }
}
