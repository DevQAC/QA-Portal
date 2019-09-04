import {Component, OnDestroy, OnInit, Output} from '@angular/core';
import {DEFAULT_CV, ICvModel} from '../_common/models/qac-cv-db.model';
import {ViewCvService} from '../_common/services/view-cv.service';
import {CvCardBaseComponent} from '../cv-card-base/cv-card-base.component';
import {IFeedback} from '../_common/models/feedback.model';
import {ActivatedRoute} from '@angular/router';
import {TRAINING_ADMIN_ROLE} from '../../../../portal-core/src/app/_common/models/portal-constants';
import {Subscription} from 'rxjs';
import {MAT_DATE_LOCALE, MatDialog} from '@angular/material';
import {SubmitConfirmDialogComponent} from './submit-confirm-dialog/submit-confirm-dialog.component';


@Component({
  selector: 'app-view-cv',
  templateUrl: './view-cv.component.html',
  styleUrls: ['./view-cv.component.scss'],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
  ]
})
export class ViewCvComponent implements OnInit, OnDestroy {
  @Output() public canComment: boolean;

  fileURL: string;
  workExpFeedbackIndex: number;
  qualFeedbackIndex: number;

  public cvData: ICvModel;
  public workExpFeedback = [];
  public qualFeedback = [];

  private cvDataSubscription$: Subscription;

  constructor(private cvService: ViewCvService,
              private activatedRoute: ActivatedRoute,
              public dialog: MatDialog) {
  }

  ngOnInit() {
    if (SubmitConfirmDialogComponent) {
      this.canComment = this.activatedRoute.snapshot.data.roles === TRAINING_ADMIN_ROLE;
    }
    this.cvDataSubscription$ = this.cvService.getLatestCvForCurrentUser().subscribe(cv => this.cvData = {...DEFAULT_CV, ...cv});
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(SubmitConfirmDialogComponent, {
      width: '250px'
    });
    dialogRef.componentInstance.canSubmit = false;
    dialogRef.componentInstance.doSubmit.subscribe(() => {
      if (dialogRef.componentInstance.canSubmit === true) {
        this.onSubmit();
      }
    });
    dialogRef.afterClosed().subscribe(() => {
    });
  }

  getPDF() {
    this.cvService.getPDFService(this.cvData).subscribe((response) => {

      const file = new Blob([response], {type: 'application/pdf'});
      console.log('it worked');

      this.fileURL = URL.createObjectURL(file);

      window.open(this.fileURL, '_blank');
      console.log('this is the URL ' + this.fileURL);
    });

  }


  ngOnDestroy(): void {
    this.cvDataSubscription$.unsubscribe();
  }

  onSave(): void {
    this.cvData.status = 'Saved';
    // save if exists, else create
    if (this.cvData.userName) {
      this.updateCv();
    } else {
      this.createCv();
    }
  }

  createCv(): void {
    this.cvService.createCv(this.cvData).subscribe(newCv => this.cvData = newCv);
    this.updateCv();
  }

  updateCv(): void {
    this.cvService.updateCv(this.cvData).subscribe(updatedCv => this.cvData = updatedCv);
  }

  submitCv(): void {
    this.cvService.submitCv(this.cvData).subscribe(updatedCv => this.cvData = updatedCv);
  }

  onApproveCv(): void {
    this.cvService.approveCv(this.cvData).subscribe(updatedCv => this.cvData = updatedCv);
  }

  onFailCv(): void {
    this.cvService.failCv(this.cvData).subscribe(updatedCv => this.cvData = updatedCv);
  }

  onSubmit(): void {
    this.submitCv();
  }

  onWorkExpFeedbackClick({index}: { index: number }, expCard: CvCardBaseComponent): void {
    this.workExpFeedbackIndex = index;
    this.workExpFeedback = this.cvData.allWorkExperience[index].workExperienceFeedback;
    expCard.drawer.open();
  }

  onWorkExpFeedbackChange(feedback: IFeedback[]): void {
    this.cvData.allWorkExperience[this.workExpFeedbackIndex].workExperienceFeedback = feedback;
  }

  onQualFeedbackClick({index}: { index: number }, qualCard: CvCardBaseComponent): void {
    this.qualFeedbackIndex = index;
    this.qualFeedback = this.cvData.allQualifications[index].qualificationFeedback;
    qualCard.drawer.open();
  }

  onQualFeedbackChange(feedback: IFeedback[]): void {
    this.cvData.allQualifications[this.qualFeedbackIndex].qualificationFeedback = feedback;
  }
}
