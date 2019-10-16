import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ApplicationService } from '../_common/services/application.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { ActivatedRoute } from '@angular/router';
import { finalize, take } from 'rxjs/operators';
import { PortalApplicationModel } from 'projects/portal-core/src/app/_common/models/portal-application.model';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';
import { PortalApplicationProjectsModel } from 'projects/portal-core/src/app/_common/models/portal-application-projects.model';
import { PortalProjectModel } from 'projects/portal-core/src/app/_common/models/portal-project.model';

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
    private errorService: QaErrorHandlerService,
    private aR: ActivatedRoute,
    private toastr: QaToastrService
  ) {
    this.appForm = new FormBuilder().group({
      name: ['', Validators.required],
      description: ['', Validators.required],
      baseUrl: ['', Validators.required],
      displayOrder: [1, Validators.required]
    });
  }

  ngOnInit() {
    const appId = this.aR.snapshot.params.id;
    this.appService.getApplicationById(appId).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      app => {
        this.app = new PortalApplicationProjectsModel();
        this.app.portalApplication = app as any;
        // this.app = app;
        this.appForm.patchValue(this.app.portalApplication);
      },
      err => this.errorService.handleError(err));
  }

  onSaveAppClicked(): void {
    const app = {
      ...this.app,
      ...this.appForm.value
    };
    this.isLoading = true;
    this.appService.saveApplication(app)
      .pipe(finalize(() => {
        this.isLoading = false;
      })).subscribe(() => {
        this.appForm.markAsPristine();
        this.toastr.showSuccess('Application updated');
      }, err => this.errorService.handleError(err));
  }

  onNewProjectClicked(): void {
    this.app.portalProjects.push(new PortalProjectModel());
  }

  onRemoveProjectClicked(): void {
    
  }

}
