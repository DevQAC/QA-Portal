import {Component, OnInit} from '@angular/core';
import {MatDialogRef} from '@angular/material';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {UserService} from '../../_common/services/user.service';
import {UserDetailsModel} from '../../../../../portal-core/src/app/_common/models/user-details.model';
import {RoleService} from '../../_common/services/role.service';
import {QaErrorHandlerService} from '../../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {UserModel} from '../../../../../portal-core/src/app/_common/models/user.model';

@Component({
  selector: 'app-add-user-dialog',
  templateUrl: './add-user-dialog.component.html'
})
export class AddUserDialogComponent implements OnInit {

  public userForm: FormGroup;

  public roles: string[];

  public isLoading = false;

  constructor(
    public dialogRef: MatDialogRef<AddUserDialogComponent>,
    private rolesService: RoleService,
    private errorService: QaErrorHandlerService,
    private userService: UserService
  ) {
    this.userForm = new FormBuilder().group({
      userName: ['', [Validators.required, Validators.email]],
      firstName: ['', [Validators.required]],
      lastName: ['', [Validators.required]],
      role: ['', [Validators.required]]
    });
  }

  ngOnInit(): void {
    this.rolesService.getPortalRoleNames().subscribe((response) => {
        this.roles = response;
      },
      (error) => {
        this.errorService.handleError(error);
      });
  }

  onSubmit(): void {
    this.isLoading = true;
    this.userForm.disable();
    this.userService.addUser(this.userForm.value as UserModel).subscribe(user => {
      this.isLoading = false;
      this.userForm.enable();
      this.dialogRef.close(user);
    });
  }
}
