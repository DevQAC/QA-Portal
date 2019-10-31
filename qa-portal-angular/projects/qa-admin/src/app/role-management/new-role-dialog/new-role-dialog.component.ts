import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RoleService } from '../../_common/services/role.service';
import { MatDialogRef } from '@angular/material';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { take, finalize, flatMap } from 'rxjs/operators';
import { ApplicationService } from '../../_common/services/application.service';
import { of, forkJoin, Observable } from 'rxjs';
import { PortalApplicationModel } from 'projects/portal-core/src/app/_common/models/portal-application.model';

@Component({
  selector: 'app-new-role-dialog',
  templateUrl: './new-role-dialog.component.html'
})
export class NewRoleDialogComponent implements OnInit {

  public roleForm: FormGroup;
  public availableApps: PortalApplicationModel[];
  public isLoading = true;

  constructor(
    private roleService: RoleService,
    private appService: ApplicationService,
    public dialogRef: MatDialogRef<NewRoleDialogComponent>,
    private errorService: QaErrorHandlerService
  ) {
    this.roleForm = new FormBuilder().group({
      name: ['', Validators.required],
      portalApplication: [null, Validators.required]
    });
  }

  ngOnInit(): void {
    this.appService.getAllApplications().pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(apps => {
      this.availableApps = apps;
    });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.roleService.addRole(this.roleForm.value).pipe(
      take(1),
      finalize(() => this.isLoading = false)
    ).subscribe(
      role => this.dialogRef.close(role),
      err => this.errorService.handleError(err)
    );
  }
}
