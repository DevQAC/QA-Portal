import { Component, OnInit, Input, ViewChild, ElementRef } from '@angular/core';
import { ENTER, COMMA } from '@angular/cdk/keycodes';
import { FormControl } from '@angular/forms';
import { MatChipInputEvent, MatAutocompleteSelectedEvent, MatAutocomplete } from '@angular/material';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';

@Component({
  selector: 'app-cohort-chips',
  templateUrl: './cohort-chips.component.html',
  styleUrls: ['./cohort-chips.component.css']
})
export class CohortChipsComponent implements OnInit {
  @Input() cohorts: string[];

  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  separatorKeysCodes: number[] = [ENTER, COMMA];
  cohortCtrl = new FormControl();
  filteredcohorts: Observable<string[]>

  allCohorts: string[] = [
    "cohort_CI_Intake_1", "cohort_CI_Intake_2", "cohort_Java_Intake_1", "cohort_Java_Intake_2", "cohort_Scala_Intake_1"
  ];

  @ViewChild('auto', { static: false }) matAutocomplete: MatAutocomplete;
  @ViewChild('cohortInput', { static: false }) cohortInput: ElementRef<HTMLInputElement>;


  constructor() {
    this.filteredcohorts = this.cohortCtrl.valueChanges.pipe(
      startWith(null),
      map((cohort: string | null) => cohort ? this._filter(cohort) : this.allCohorts.slice()));
  }

  ngOnInit() {
  }

  add(event: MatChipInputEvent): void {
    if (!this.matAutocomplete.isOpen) {
      const input = event.input;
      const value = event.value;

      // Add our role
      if ((value || '').trim()) {
        this.cohorts.push(value.trim());
      }

      // Reset the input value
      if (input) {
        input.value = '';
      }

      this.cohortCtrl.setValue(null);
    }
  }

  remove(cohort: string): void {
    const index = this.cohorts.indexOf(cohort);

    if (index >= 0) {
      this.cohorts.splice(index, 1);
    }
  }

  selected(event: MatAutocompleteSelectedEvent): void {
    this.cohorts.push(event.option.viewValue);
    this.cohortInput.nativeElement.value = '';
    this.cohortCtrl.setValue(null);
  }

  private _filter(value: string): string[] {
    const filterValue = value.toLowerCase();

    return this.allCohorts.filter(cohort => cohort.toLowerCase().indexOf(filterValue) === 0);
  }
}

