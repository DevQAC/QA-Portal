import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ISkills } from '../_common/models/skills.model';
import { COMMA, ENTER } from '@angular/cdk/keycodes';
import { MatChipInputEvent } from '@angular/material/chips';

@Component({
  selector: 'app-cv-skills',
  templateUrl: './cv-skills.component.html',
  styleUrls: ['./cv-skills.component.css']
})
export class CvSkillsComponent implements OnInit {
  @Input() skills: ISkills;
  @Output() skillsChange = new EventEmitter<ISkills>();
  @Input() canEdit: boolean;

  public skillCategories = [
    {
      key: 'programmingLanguages',
      label: 'Programming Languages'
    },
    {
      key: 'ides',
      label: 'IDEs'
    },
    {
      key: 'devops',
      label: 'Devops'
    },
    {
      key: 'databases',
      label: 'Databases'
    },
    {
      key: 'platforms',
      label: 'Platforms'
    },
    {
      key: 'operatingSystems',
      label: 'Operating Systems'
    }
  ]


  visible = true;
  selectable = true;
  removable = true;
  addOnBlur = true;
  readonly separatorKeysCodes: number[] = [ENTER, COMMA];

  add(event: MatChipInputEvent): void {
    const input = event.input;
    const value = event.value;

    // Add our fruit
    if ((value || '').trim()) {
      this.skills.other.push(value.trim());
    }

    // Reset the input value
    if (input) {
      input.value = '';
    }
  }

  remove(others: string): void {
    const index = this.skills.other.indexOf(others);

    if (index >= 0) {
      this.skills.other.splice(index, 1);
    }
  }


  constructor() { }

  ngOnInit() {
  }

  onInputChange(data) {
    this.skills.other = data;
    this.skillsChange.emit(this.skills);
  }

  isDisabled() {
    return !this.canEdit;
  }

}


