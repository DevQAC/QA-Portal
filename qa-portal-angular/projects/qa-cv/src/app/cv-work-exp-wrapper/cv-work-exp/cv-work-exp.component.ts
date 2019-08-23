import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IWorkExperience } from '../../_common/models/work-experience.model';

@Component({
  selector: 'app-cv-work-exp',
  templateUrl: './cv-work-exp.component.html',
  styleUrls: ['./cv-work-exp.component.css']
})
export class CvWorkExpComponent implements OnInit {
  @Input() experience: IWorkExperience;
  @Output() experienceChange = new EventEmitter<IWorkExperience>();

  @Output() experienceDelete = new EventEmitter<IWorkExperience>();

  constructor() { }

  ngOnInit() {
  }

  onTitleChange(input) {
    this.experience.job = input;
    this.announceChange();
  }

  onStartDateChange(input) {
    this.experience.start_date = input;
    this.announceChange();
  }

  onEndDateChange(input) {
    this.experience.end_date = input;
    this.announceChange();
  }

  onDetailChange(input) {
    this.experience.detail = input;
    this.announceChange();
  }

  announceChange(): void {
    this.experienceChange.emit(this.experience);
  }

  deleteExp(): void {
    this.experienceDelete.emit(this.experience);
  }
}
