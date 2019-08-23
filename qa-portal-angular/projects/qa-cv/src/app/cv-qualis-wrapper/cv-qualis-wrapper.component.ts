import { Component, Input, Output, EventEmitter } from '@angular/core';
import { IQualification } from '../_common/models/qualification.model';

@Component({
  selector: 'app-cv-qualis-wrapper',
  templateUrl: './cv-qualis-wrapper.component.html',
  styleUrls: ['./cv-qualis-wrapper.component.css']
})
export class CvQualisWrapperComponent {
  @Input() qualifications: IQualification[];
  @Output() qualificationsChange = new EventEmitter<IQualification[]>();

  onNewQualiClick(): void {
    this.qualifications = [{
      q_detail: '<<NEW QUALI DEFAULT>>',
      feedback: []
    }, ...this.qualifications];
    this.qualificationsChange.emit(this.qualifications);
  }

  onDeleteQualiEvent(index: number): void {
    this.qualifications.splice(index, 1);
    this.qualificationsChange.emit(this.qualifications);
  }

}
