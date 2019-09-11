import { Component, Input, Output, EventEmitter } from '@angular/core';

import { IWorkExperience } from '../_common/models/work-experience.model';
import { MatTableDataSource } from '@angular/material';
import { animate, state, style, transition, trigger } from '@angular/animations';

import * as moment from 'moment';

@Component({
  selector: 'app-cv-work-exp',
  templateUrl: './cv-work-exp.component.html',
  animations: [
    trigger('detailExpand', [
      state('collapsed', style({ height: '0px', minHeight: '0' })),
      state('expanded', style({ height: '*' })),
      transition('expanded <=> collapsed', animate('225ms cubic-bezier(0.4, 0.0, 0.2, 1)')),
    ]),
  ]
})
export class CvWorkExpComponent {
  @Input() set experiences(workExp: IWorkExperience[]) {
    this.workExperienceTableDataSource.data = workExp;
  }

  get experiences(): IWorkExperience[] {
    return this.workExperienceTableDataSource.data;
  }

  @Output() experiencesChange = new EventEmitter<IWorkExperience[]>();

  @Output() feedbackClick = new EventEmitter<{ index: number, experience: IWorkExperience }>();

  @Input() canEdit: boolean;

  public workExperienceTableDataSource = new MatTableDataSource<IWorkExperience>();
  public expandedElement: IWorkExperience;
  public displayedColumns = ['title', 'remove', 'feedback'];


  onNewWorkExperienceClick(): void {
    const lastExp = this.experiences[this.experiences.length - 1];

    if (!lastExp || lastExp.jobTitle || lastExp.end || lastExp.start || lastExp.workExperienceDetails) {
      this.experiences = [
        ...this.experiences, {
          jobTitle: '',
          start: '',
          end: '',
          workExperienceDetails: '',
          workExperienceFeedback: []
        }];
      this.experiencesChange.emit(this.experiences);
    }
  }

  public onRemoveWorkExperienceClicked(index: number): void {
    this.workExperienceTableDataSource.data.splice(index, 1); // setters don't get called by higher order functions so do it directly
    this.workExperienceTableDataSource._updateChangeSubscription(); // force the table to update (it doesn't auto detect splices)
    this.announceChange();
  }

  onFeedbackButtonClicked(event: MouseEvent, index: number, experience: IWorkExperience): void {
    event.stopPropagation(); // Prevent event bubbling to the row below it.
    this.expandedElement = experience;
    this.feedbackClick.emit({ index, experience });
  }

  isDisabled() {
    return !this.canEdit;
  }

  public announceChange() {
    this.experiencesChange.emit(this.experiences);
    console.log(this.experiences);
  }

  public getFormattedDateRange({ start, end }: IWorkExperience): string {
    const dateFormat = 'MMM GGGG';
    const startMom = moment(start);
    const endMom = moment(end);

    if (startMom.isValid() || endMom.isValid()) {
      const formattedStart = startMom.format(dateFormat);
      const formattedEnd = endMom.format(dateFormat);
      return `${startMom.isValid() && formattedStart} - ${endMom.isValid() && formattedEnd}`;
    } else {
      return '';
    }
  }
}
