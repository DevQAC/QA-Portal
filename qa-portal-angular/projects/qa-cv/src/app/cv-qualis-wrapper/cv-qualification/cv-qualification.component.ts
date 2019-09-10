import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IQualification } from '../../_common/models/qualification.model';


@Component({
  selector: 'app-cv-qualis',
  templateUrl: './cv-qualification.component.html',
  styleUrls: ['./cv-qualification.component.css']
})
export class CvQualificationComponent implements OnInit {
  @Input() qualification: IQualification;

  @Input() canEdit: boolean;

  @Output() qualificationsChange = new EventEmitter<IQualification>();

  @Output() qualificationDeletion = new EventEmitter<IQualification>();

  @Output() feedbackClick = new EventEmitter<IQualification>();

  constructor() { }

  deleteQualification(): void {
    this.qualificationDeletion.emit(this.qualification);
  }

  ngOnInit() {
  }

  onInputChange(data) {
    this.qualification.qualificationDetails = data;
    this.qualificationsChange.emit(this.qualification);
  }

  onFeedbackClick(): void {
    this.feedbackClick.emit(this.qualification);
  }

  isDisabled() {
    return !this.canEdit;
  }

}


