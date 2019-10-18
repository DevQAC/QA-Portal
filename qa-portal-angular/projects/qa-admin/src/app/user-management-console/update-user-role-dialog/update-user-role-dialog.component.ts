import {Component, Inject, OnInit} from '@angular/core';
import {MatDialogRef, MAT_DIALOG_DATA} from '@angular/material';
import {UpdateUserCohortDialogComponent} from '../update-user-cohort-dialog/update-user-cohort-dialog.component';
import {RoleService} from '../../_common/services/role.service';
import {QaErrorHandlerService} from '../../../../../portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-update-user-role-dialog',
  templateUrl: './update-user-role-dialog.component.html'
})
export class UpdateUserRoleDialogComponent implements OnInit {

  public roleSelection = '';

  public roles = [];

  constructor(
    public dialogRef: MatDialogRef<UpdateUserCohortDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: string,
    private rolesService: RoleService,
    private errorService: QaErrorHandlerService) {
  }

  ngOnInit(): void {
    this.rolesService.getPortalRoleNames().subscribe((response: string[]) => {
        this.roles = response;
      },
      (error) => {
        this.errorService.handleError(error);
      });
  }
}
