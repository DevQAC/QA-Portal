import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IWorkExperience } from '../_common/models/work-experience.model';

@Component({
  selector: 'app-cv-work-exp-wrapper',
  templateUrl: './cv-work-exp-wrapper.component.html',
  styleUrls: ['./cv-work-exp-wrapper.component.css']
})
export class CvWorkExpWrapperComponent implements OnInit {
  @Input() experiences: IWorkExperience[];
  @Output() experiencesChange = new EventEmitter<IWorkExperience[]>();

  constructor() { }

  ngOnInit() {

  }

  onInputChange(data) {
    this.experiences = data;
    this.experiencesChange.emit(this.experiences);
  }
}
