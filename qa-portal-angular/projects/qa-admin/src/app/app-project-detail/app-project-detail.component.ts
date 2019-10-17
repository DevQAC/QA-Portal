import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../_common/services/application.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { ActivatedRoute } from '@angular/router';
import { finalize, take } from 'rxjs/operators';
import { PortalProjectModel } from 'projects/portal-core/src/app/_common/models/portal-project.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

@Component({
  selector: 'app-app-project-detail',
  templateUrl: './app-project-detail.component.html',
  styleUrls: ['./app-project-detail.component.css']
})
export class AppProjectDetailComponent implements OnInit {

  public projectForm: FormGroup;
  public isLoading = true;
  public project: PortalProjectModel;

  constructor(
    private appService: ApplicationService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService) {
    this.projectForm = new FormBuilder().group({
      name: ['', Validators.required]
    });
  }

  ngOnInit() {

    const projectId = this.aR.snapshot.params.id;
    this.appService.getProjectById(projectId).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      project => {
        this.project = project;
        this.projectForm.patchValue(this.project);
        console.log(this.project);
      },
      err => this.errorService.handleError(err));
  }

}
