import { Component, Input, Output, EventEmitter } from '@angular/core';
import { IQualification } from '../_common/models/qualification.model';

@Component({
  selector: 'app-cv-qualis-wrapper',
  templateUrl: './cv-qualification-wrapper.component.html',
  styleUrls: ['./cv-qualification-wrapper.component.css']
})
export class CvQualificationWrapperComponent {
  @Input() qualifications: IQualification[];
  @Output() qualificationsChange = new EventEmitter<IQualification[]>();
  @Output() feedbackClick = new EventEmitter<{index: number, qualifications: IQualification}>();
  @Input() canEdit: boolean;

  onNewQualiClick(): void {
    this.qualifications = [{
      qualificationDetails: '',
      qualificationFeedback: []
    }, ...this.qualifications];
    this.qualificationsChange.emit(this.qualifications);
  }

  onDeleteQualiEvent(index: number): void {
    this.qualifications.splice(index, 1);
    this.qualificationsChange.emit(this.qualifications);
  }

  onFeedbackClick(index: number, qualifications: IQualification): void {
    this.feedbackClick.emit({index, qualifications});
  }

  isDisabled() {
    return !this.canEdit;
  }

}
