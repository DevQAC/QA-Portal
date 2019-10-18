import { Component, OnInit } from '@angular/core';
import { ApplicationService } from '../_common/services/application.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { ActivatedRoute } from '@angular/router';
import { finalize, take } from 'rxjs/operators';
import { PortalProjectModel } from 'projects/portal-core/src/app/_common/models/portal-project.model';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { customIcons, CustomIconModel } from 'projects/qa-common/src/app/_common/models/icons.model';
import { ProjectPageModel } from 'projects/portal-core/src/app/_common/models/project-page.model';
import { forkJoin } from 'rxjs';
import { RoleService } from '../_common/services/role.service';
import { RoleModel } from 'projects/portal-core/src/app/_common/models/role.model';
import { MatDialog } from '@angular/material';
import { DeletePageConfirmDialogComponent } from './delete-page-confirm-dialog/delete-page-confirm-dialog.component';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';

@Component({
  selector: 'app-app-project-detail',
  templateUrl: './app-project-detail.component.html'
})
export class AppProjectDetailComponent implements OnInit {
  public projectForm: FormGroup;
  public isLoading = true;
  public project: PortalProjectModel;
  public roles: string[];
  public allIcons = customIcons;

  constructor(
    private appService: ApplicationService,
    private roleService: RoleService,
    private aR: ActivatedRoute,
    public dialog: MatDialog,
    private toastr: QaToastrService,
    private errorService: QaErrorHandlerService) {
    this.projectForm = new FormBuilder().group({
      name: ['', Validators.required]
    });
  }

  ngOnInit() {
    const projectId = this.aR.snapshot.params.id;
    forkJoin(
      this.appService.getProjectById(projectId),
      this.roleService.getPortalRoleNames()
    ).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      ([project, roles]) => {
        this.project = project;
        this.roles = roles;
        this.projectForm.patchValue(this.project);
      },
      err => this.errorService.handleError(err));
  }

  getIconByName(name: string): CustomIconModel {
    return this.allIcons.find(icon => icon.name === name);
  }

  onSaveProjectClicked(): void {
    const project = {
      ...this.project,
      ...this.projectForm.value
    };
    this.isLoading = true;
    this.appService.saveProject(project).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(() => {
      this.projectForm.markAsPristine();
      this.toastr.showSuccess('Project updated');
    }, err => this.errorService.handleError(err));
  }

  onNewPageClicked(): void {
    this.project.projectPages.push(new ProjectPageModel());
    this.projectForm.markAsDirty();
  }

  onRemovePageClicked(page: ProjectPageModel): void {
    this.dialog.open(DeletePageConfirmDialogComponent).afterClosed().pipe(take(1)).subscribe(data => {
      if (data) {
        this.project.projectPages = this.project.projectPages.filter(p => p.id !== page.id);
        this.projectForm.markAsDirty();
      }
    });
  }
}
