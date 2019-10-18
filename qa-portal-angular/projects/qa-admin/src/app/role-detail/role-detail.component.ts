import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RoleModel } from 'projects/portal-core/src/app/_common/models/role.model';
import { RoleService } from '../_common/services/role.service';
import { ActivatedRoute } from '@angular/router';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';
import { finalize } from 'rxjs/operators';
import { ApplicationService } from '../_common/services/application.service';
import { PortalApplicationModel } from 'projects/portal-core/src/app/_common/models/portal-application.model';
import { forkJoin } from 'rxjs';

import * as _ from 'lodash';

@Component({
  selector: 'app-role-detail',
  templateUrl: './role-detail.component.html',
  styleUrls: ['./role-detail.component.css']
})
export class RoleDetailComponent implements OnInit {
  public roleForm: FormGroup;

  public role: RoleModel;
  public availableApps: PortalApplicationModel[];

  public isLoading = true;

  constructor(
    private roleService: RoleService,
    private appService: ApplicationService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private toastr: QaToastrService
  ) {
    this.roleForm = new FormBuilder().group({
      name: ['', Validators.required],
      portalApplication: [null, Validators.required]
    });
    this.roleForm.disable();

  }

  ngOnInit() {
    forkJoin(
      this.roleService.getRoleById(this.aR.snapshot.params.id),
      this.appService.getAllApplications()
    )
      .pipe(finalize(() => {
        this.roleForm.enable();
        this.isLoading = false;
      }))
      .subscribe(
        ([role, apps]) => {
          this.role = role;
          this.availableApps = apps;
          this.roleForm.patchValue({
            ...this.role,
            portalApplication: _.get(this.role, ['portalApplication', 'id'], null)
          });
        }, err => this.errorService.handleError(err)
      );
  }

  public onSaveRoleClicked() {
    this.role = {
      ...this.role,
      ...this.roleForm.value,
      portalApplication: this.availableApps.find(app => app.id === this.roleForm.value.portalApplication)
    };

    this.roleService.saveRole(this.role)
      .pipe(finalize(() => {
        this.roleForm.enable();
        this.isLoading = false;
      })).subscribe(() => {
        this.toastr.showSuccess('Role updated');
      }, err => this.errorService.handleError(err));
  }

}
