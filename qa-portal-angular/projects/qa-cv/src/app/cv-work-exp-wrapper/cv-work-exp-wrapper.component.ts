
import { Component, OnInit, Input, Output, EventEmitter, ViewChild } from '@angular/core';

import { IWorkExperience } from '../_common/models/work-experience.model';

@Component({
  selector: 'app-cv-work-exp-wrapper',
  templateUrl: './cv-work-exp-wrapper.component.html',
  styleUrls: ['./cv-work-exp-wrapper.component.css']
})
export class CvWorkExpWrapperComponent implements OnInit {
  @Input() experiences: IWorkExperience[];
  @Output() experiencesChange = new EventEmitter<IWorkExperience[]>();

  @Output() feedbackClick = new EventEmitter<{index: number, experience: IWorkExperience}>();



  constructor() { }

  ngOnInit() {

  }

  onInputChange(data: IWorkExperience, index: number): void {
    this.experiences[index] = data;
    this.experiencesChange.emit(this.experiences);
    // console.info(this.experiences, data, index);
  }

  addExp(): void {
    this.experiences = [{
      jobTitle: "",
      start: "",
      end: "",
      workExperienceDetails: "",
      workExperienceFeedback: []
    }, ...this.experiences]
    this.experiencesChange.emit(this.experiences)
  }

  delExp(index: number): void {
    this.experiences.splice(index, 1);
    this.experiencesChange.emit(this.experiences);
    console.info(this.experiences, index);
  }

  onFeedbackClick(index: number, experience: IWorkExperience): void {
    this.feedbackClick.emit({index, experience});

   
  }
}
