import { Component, OnInit, Output, OnDestroy } from '@angular/core';
import { ICvModel, DEFAULT_CV } from '../_common/models/qac-cv-db.model';
import { ViewCvService } from '../_common/services/view-cv.service';
import { CvCardBaseComponent } from '../cv-card-base/cv-card-base.component';
import { IFeedback } from '../_common/models/feedback.model';
import { ActivatedRoute } from '@angular/router';
import { TRAINING_ADMIN_ROLE } from '../../../../portal-core/src/app/_common/models/portal-constants';
import { Observable, Subscription } from 'rxjs';
import { MAT_DATE_LOCALE } from '@angular/material';


@Component({
  selector: 'app-view-cv',
  templateUrl: './view-cv.component.html',
  styleUrls: ['./view-cv.component.scss'],
  providers: [
    { provide: MAT_DATE_LOCALE, useValue: 'en-GB' },
  ]
})
export class ViewCvComponent implements OnInit, OnDestroy {
  @Output() public canComment: boolean;
  cvs: ICvModel[] = [];
  openThis = false;

  public cvData: ICvModel;
  public workExpFeedback = [];
  workExpFeedbackIndex: number;
  public workExpDrawerOpen = false;

  public qualFeedback = [];
  qualFeedbackIndex: number;
  public qualDrawerOpen = false;

  private cvDataSubscription$: Subscription;

  constructor(
    private cvService: ViewCvService,
    private activatedRoute: ActivatedRoute
  ) { }

  ngOnInit() {
    this.canComment = this.activatedRoute.snapshot.data.roles === TRAINING_ADMIN_ROLE;

    this.cvDataSubscription$ = this.cvService.getLatestCvForCurrentUser().subscribe(cv => this.cvData = { ...DEFAULT_CV, ...cv });
  }

  ngOnDestroy(): void {
    this.cvDataSubscription$.unsubscribe();
  }

  onSave(): void {
    this.cvData.versionNumber = this.cvData.versionNumber ? this.cvData.versionNumber + 1 : 1;
    this.cvService.updateCv(this.cvData).subscribe(updatedCv => this.cvData = updatedCv);
  }

  onWorkExpFeedbackClick({ index }: { index: number }, expCard: CvCardBaseComponent): void {
    this.workExpFeedbackIndex = index;
    this.workExpFeedback = this.cvData.allWorkExperience[index].workExperienceFeedback;
    expCard.drawer.open();
  }

  onWorkExpFeedbackChange(feedback: IFeedback[]): void {
    this.cvData.allWorkExperience[this.workExpFeedbackIndex].workExperienceFeedback = feedback;
  }

  onQualFeedbackClick({ index }: { index: number }, qualCard: CvCardBaseComponent): void {
    this.qualFeedbackIndex = index;
    this.qualFeedback = this.cvData.allQualifications[index].qualificationFeedback;
    qualCard.drawer.open();
  }

  onQualFeedbackChange(feedback: IFeedback[]): void {
    this.cvData.allQualifications[this.qualFeedbackIndex].qualificationFeedback = feedback;
  }
}
