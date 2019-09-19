import { Component, Output, EventEmitter, OnInit } from '@angular/core';
import { MatDialogRef } from '@angular/material';
import { UserModel } from 'projects/portal-core/src/app/_common/models/user.model';
import { FormBuilder, Validators, FormGroup } from '@angular/forms';
import { UserService } from '../../_common/services/user.service';

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
    this.roles = [
      'This',
      'Component',
      'Is',
      'Not',
      'Yet',
      'Implemented'
    ];
  }

  onSubmit(): void {
    this.isLoading = true;
    this.userForm.disable();
    this.userService.createUser(this.userForm.value as UserModel).subscribe(user => {
      this.isLoading = false;
      this.userForm.enable();
      this.dialogRef.close(user);
    });
  }

}
