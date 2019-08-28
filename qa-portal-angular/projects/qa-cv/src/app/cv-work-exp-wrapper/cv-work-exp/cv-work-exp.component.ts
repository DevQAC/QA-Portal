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

  @Output() feedbackClick = new EventEmitter<IWorkExperience>();

  constructor() { }

  ngOnInit() {
  }
  panelOpenState: boolean = false;
  buttonClickedState: boolean = false;

  onTitleChange(input) {
    this.experience.jobTitle = input;
    this.announceChange();
  }

  onStartDateChange(input) {
    this.experience.start = input;
    this.announceChange();
  }

  onEndDateChange(input) {
    this.experience.end = input;
    this.announceChange();
  }

  onDetailChange(input) {
    this.experience.workExperienceDetails = input;
    this.announceChange();
  }

  announceChange(): void {
    this.experienceChange.emit(this.experience);
  }

  deleteExp(): void {
    this.experienceDelete.emit(this.experience);
  }

  onFeedbackClick(): void {
    this.panelOpenState = false;
    this.buttonClickedState = true;
    this.feedbackClick.emit(this.experience);
    this.buttonClickedState = false;
  }

  checkButtonState(): void {
    if (!this.buttonClickedState) {
      this.panelOpenState = !this.panelOpenState;
    }
  }
}