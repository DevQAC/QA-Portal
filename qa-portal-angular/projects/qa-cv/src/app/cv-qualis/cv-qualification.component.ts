import { Component, Input, Output, EventEmitter } from '@angular/core';
import { IQualification } from '../_common/models/qualification.model';
import { MatTableDataSource } from '@angular/material';

@Component({
  selector: 'app-cv-qualis',
  templateUrl: './cv-qualification.component.html'
})
export class CvQualificationComponent {

  @Input() set qualifications(qualis: IQualification[]) {
    this.qualificationsTableDataSource.data = qualis;
  }

  get qualifications(): IQualification[] {
    return this.qualificationsTableDataSource.data;
  }

  @Output() qualificationsChange = new EventEmitter<IQualification[]>();

  @Output() feedbackClick = new EventEmitter<{ index: number, qualifications: IQualification }>();

  @Input() canEdit: boolean;

  public qualificationsTableDataSource = new MatTableDataSource<IQualification>();

  onNewQualiClick(): void {
    this.qualifications = [...this.qualifications,
    {
      qualificationDetails: '',
      qualificationFeedback: []
    }];
    this.qualificationsChange.emit(this.qualifications);
  }

  onFeedbackButtonClicked(index: number, qualifications: IQualification): void {
    this.feedbackClick.emit({ index, qualifications });
  }


  public announceChange() {
    this.qualificationsChange.emit(this.qualifications);
  }

  public onQualificationDetailsInputChange(qualification: IQualification, value: string) {
    qualification.qualificationDetails = value;
    this.announceChange();
  }

  public onRemoveQualificationClicked(index: number): void {
    this.qualificationsTableDataSource.data.splice(index, 1); // setters don't get called by higher order functions so do it directly
    this.qualificationsTableDataSource._updateChangeSubscription(); // force the table to update (it doesn't auto detect splices)
    this.announceChange();
  }

  isDisabled() {
    return !this.canEdit;
  }

}
