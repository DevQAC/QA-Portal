import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { PortalApplicationProjectsModel } from 'projects/portal-core/src/app/_common/models/portal-application-projects.model';
import { ApplicationService } from '../_common/services/application.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { ActivatedRoute } from '@angular/router';
import { finalize, take } from 'rxjs/operators';

@Component({
  selector: 'app-application-detail',
  templateUrl: './application-detail.component.html'
})
export class ApplicationDetailComponent implements OnInit {

  public appForm: FormGroup;
  public isLoading = true;

  public app: PortalApplicationProjectsModel;

  constructor(
    private appService: ApplicationService,
    private errorHandler: QaErrorHandlerService,
    private aR: ActivatedRoute
  ) {
    this.appForm = new FormBuilder().group({
      name: ['', Validators.required]
    });
  }

  ngOnInit() {
    const appId = this.aR.snapshot.params.id;
    this.appService.getApplicationById(appId).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      app => {
        this.app = app;
        this.appForm.patchValue(this.app.portalApplication);
      },
      err => this.errorHandler.handleError(err));
  }

}
