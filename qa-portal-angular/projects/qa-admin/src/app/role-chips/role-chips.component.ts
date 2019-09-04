import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { FormControl } from '@angular/forms';
import { MatChipInputEvent, MatAutocompleteSelectedEvent, MatAutocomplete } from '@angular/material';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';

@Component({
  selector: 'app-role-chips',
  templateUrl: './role-chips.component.html',
  styleUrls: ['./role-chips.component.css']
})
export class RoleChipsComponent implements OnInit {
  @Input() roles: string[];

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  roleCtrl = new FormControl();
  filteredRoles: Observable<string[]>

  allRoles: string[] = [
    "cohort_CI_Intake_1", "cohort_CI_Intake_2", "cohort_Java_Intake_1", "cohort_Java_Intake_2", "cohort_Scala_Intake_1", "finance-admin", "finance-manager", "finance-user", "hr-admin", "hr-manager", "hr-user", "offline_access", "super-user", "training-admin", "training-manager", "training-user", "uma_authorization"
  ];

  @ViewChild('auto', { static: false }) matAutocomplete: MatAutocomplete;
  @ViewChild('roleInput', { static: false }) roleInput: ElementRef<HTMLInputElement>;


  constructor() {
    this.filteredRoles = this.roleCtrl.valueChanges.pipe(
      startWith(null),
      map((role: string | null) => role ? this._filter(role) : this.allRoles.slice()));
  }

  ngOnInit() {
  }

  add(event: MatChipInputEvent): void {
    if (!this.matAutocomplete.isOpen) {
      const input = event.input;
      const value = event.value;

      // Add our role
      if ((value || '').trim()) {
        this.roles.push(value.trim());
      }

      // Reset the input value
      if (input) {
        input.value = '';
      }

      this.roleCtrl.setValue(null);
    }
  }

  remove(role: string): void {
    const index = this.roles.indexOf(role);

    if (index >= 0) {
      this.roles.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.roles.push(event.option.viewValue);
    this.roleInput.nativeElement.value = '';
    this.roleCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allRoles.filter(role => role.toLowerCase().indexOf(filterValue) === 0);
  }
}

