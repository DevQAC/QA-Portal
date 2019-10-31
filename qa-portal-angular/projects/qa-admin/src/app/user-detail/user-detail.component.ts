import { Component, OnInit } from '@angular/core';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { RoleService } from '../_common/services/role.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import { UserService } from '../_common/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { forkJoin } from 'rxjs';
import { take } from 'rxjs/operators';

import * as _ from 'lodash';
import { UserDetailsModel } from 'projects/portal-core/src/app/_common/models/user-details.model';
import { QaToastrService } from 'projects/portal-core/src/app/_common/services/qa-toastr.service';

@Component({
  selector: 'app-user-detail',
  templateUrl: './user-detail.component.html'
})
export class UserDetailComponent implements OnInit {

  public userForm: FormGroup;
  public roles: string[];
  public isLoading = true;

  private fetchedUser: UserDetailsModel;

  constructor(
    private rolesService: RoleService,
    private userService: UserService,
    private aR: ActivatedRoute,
    private errorService: QaErrorHandlerService,
    private toastr: QaToastrService) {
    this.userForm = new FormBuilder().group({
      user: new FormBuilder().group(
        {
          userName: [''],
          firstName: ['', [Validators.required]],
          lastName: ['', [Validators.required]],
        }
      ),
      roleNames: [[]]
    });

    this.userForm.disable();
  }

  ngOnInit() {
    forkJoin(
      this.rolesService.getPortalRoleNames(),
      this.userService.getUserByUsername(this.aR.snapshot.params.id)
    ).pipe(take(1))
      .subscribe(([roles, user]) => {
        this.roles = roles;
        this.fetchedUser = user;

        this.userForm.patchValue({ ...user });
        this.isLoading = false;
        this.userForm.enable();
      },
        (err) => this.errorService.handleError(err)
      );
  }


  public onSubmit() {
    this.isLoading = true;
    this.userForm.disable();
    const userData: UserDetailsModel = {
      ...this.fetchedUser,
      ...this.userForm.value,
      user: {
        ...this.fetchedUser.user,
        ...this.userForm.value.user
      }
    };
    this.userService.updateUser(userData).pipe(take(1))
      .subscribe(user => {
        this.userForm.patchValue({ user });
        this.isLoading = false;
        this.userForm.enable();
        this.toastr.showSuccess('User updated');
      }, err => this.errorService.handleError(err));
  }
}
