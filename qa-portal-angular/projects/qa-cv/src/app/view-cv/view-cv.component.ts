import {Component, OnDestroy, OnInit, Output} from '@angular/core';
import {DEFAULT_CV, ICvModel} from '../_common/models/qac-cv-db.model';
import {ViewCvService} from '../_common/services/view-cv.service';
import {CvCardBaseComponent} from '../cv-card-base/cv-card-base.component';
import {IFeedback} from '../_common/models/feedback.model';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {Subscription} from 'rxjs';
import {MAT_DATE_LOCALE, MatDialog} from '@angular/material';
import {SubmitConfirmDialogComponent} from './submit-confirm-dialog/submit-confirm-dialog.component';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {IQualification} from '../_common/models/qualification.model';
import {IWorkExperience} from '../_common/models/work-experience.model';
import {UserSkillsModel} from '../_common/models/user-skills.model';
import {TRAINEE_ROLE, TRAINING_ADMIN_ROLE} from '../../../../portal-core/src/app/_common/models/portal-constants';

@Component({
  selector: 'app-view-cv',
  templateUrl: './view-cv.component.html',
  styleUrls: ['./view-cv.component.scss'],
  providers: [
    {provide: MAT_DATE_LOCALE, useValue: 'en-GB'},
  ]
})
export class ViewCvComponent implements OnInit, OnDestroy {

  @Output() public canComment = false;
  @Output() public canEdit = true;

  private TRAINING_USER_EDIT_STATES = [
    'In Progress',
    'Failed Review'
  ];

  private ADMIN_USER_EDIT_STATES = [
    'For Review'
  ];

  useTemplate = true;

  isTraineeView = true;

  loadingData = true;

  fileURL: string;

  qualFeedbackIndex: number;

  workExpFeedbackIndex: number;

  public cvData: ICvModel;

  public workExpFeedback = [];

  public qualFeedback = [];

  private cvDataSubscription: Subscription;

  private traineeSkillsSubcription: Subscription;

  private adminCvSubscription: Subscription;

  constructor(
    private cvService: ViewCvService,
    private errorHandlerService: QaErrorHandlerService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    public dialog: MatDialog) {
  }

  ngOnInit() {
    this.setRoleForPage();   // Is page being displayed for Trainee of Admin
    if (this.isTraineeView) {
      this.initialiseForTrainee();
    } else {
      this.initialiseForAdmin();
    }
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
      this.fileURL = URL.createObjectURL(file);
      window.open(this.fileURL, '_blank');
    });
  }

  ngOnDestroy(): void {
    if (!!this.cvDataSubscription) {
      this.cvDataSubscription.unsubscribe();
    }

    if (!!this.traineeSkillsSubcription) {
      this.cvDataSubscription.unsubscribe();
    }

    if (!!this.adminCvSubscription) {
      this.adminCvSubscription.unsubscribe();
    }
  }

  initialiseNewCv() {
    const tempCv = this.cvData;
    const emptyCv = null;
    if (!!this.useTemplate) {
      this.cvData = {...DEFAULT_CV, ...this.cvData};
      this.cvData.status = 'In Progress';
      this.cvData.id = null;
      this.cvData.versionNumber = null;
    } else {
      this.cvData = {...DEFAULT_CV, ...emptyCv};
      this.cvData.status = 'In Progress';
      this.cvData.allSkills = tempCv.allSkills;
      this.cvData.fullName = tempCv.fullName;
    }
    this.setEditStatus();
  }

  onSave(): void {
    if (!this.cvData.id) {
      this.cvData.status = 'In Progress';
      this.createCv();
    } else {
      this.updateCv();
    }
  }

  onSubmit(): void {
    this.cvData.status = 'For Review';
    if (!this.cvData.id) {
      this.createCv();
    } else {
      this.updateCv();
    }
  }

  createCv(): void {
    this.cvService.createCv(this.cvData).subscribe(
      (response) => {
        this.populateResponse(response);
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

  updateCv(): void {
    this.cvService.updateCv(this.cvData).subscribe(
      (response) => {
        this.populateResponse(response);
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

  onApproveCv(): void {
    this.cvData.status = 'Approved';
    this.updateCv();
    this.navigateToAdminSearch();
  }

  onFailCv(): void {
    this.cvData.status = 'Failed Review';
    this.updateCv();
    this.navigateToAdminSearch();
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

  useTemplateChanged() {
    this.useTemplate = !this.useTemplate;
  }

  private populateSkillsForTrainee() {
    this.traineeSkillsSubcription = this.cvService.getSkillsForTrainee().subscribe((userSkillsModel: UserSkillsModel) => {
      Object.keys(this.cvData.allSkills[0]).forEach((skillCategory) => {
        this.cvData.userName = userSkillsModel.userName;
        this.cvData.firstName = userSkillsModel.userFirstName;
        this.cvData.surname = userSkillsModel.userLastName;
        this.cvData.fullName = this.cvData.firstName + ' ' + this.cvData.surname;
        this.cvData.status = 'In Progress';
        this.cvData.allSkills[0][skillCategory] = this.getSkillsArrayForTechnology(userSkillsModel.skills[skillCategory]);
      });
      this.setEditStatus();
      this.loadingData = false;
    });
  }

  private getSkillsArrayForTechnology(skills: any[]): string[] {
    const skillsArr = [];
    if (!!skills) {
      skills.forEach(s => {
        skillsArr.push(s.technologyName);
      });
    }
    return skillsArr;
  }

  private initialiseForTrainee() {
    this.cvDataSubscription = this.cvService.getCurrentCvForCurrentUser().subscribe(
      (cv) => {
        this.cvData = {...DEFAULT_CV, ...cv};
        if (!cv) {
          this.populateSkillsForTrainee();
        } else {
          this.setEditStatus();
          this.loadingData = false;
        }
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      });
  }

  private initialiseForAdmin() {
    this.activatedRoute.paramMap.subscribe(
      (paramMap: ParamMap) => {
        this.adminCvSubscription = this.cvService.getCvForId(paramMap.get('id')).subscribe(
          (response) => {
            this.cvData = response;
            this.setEditStatus();
            this.setCommentStatus(); // Can user add comments to Cv
            this.loadingData = false;
          },
          (error) => {
            this.loadingData = false;
            this.errorHandlerService.handleError(error);
          });
      });
  }

  private setRoleForPage() {
    if (this.activatedRoute.snapshot.data.roles[0] === TRAINING_ADMIN_ROLE) {
      this.isTraineeView = false;
    }
  }

  setEditStatus(): void {
    this.canEdit = false;
    if (this.activatedRoute.snapshot.data.roles[0] === TRAINING_ADMIN_ROLE) {
      if (this.ADMIN_USER_EDIT_STATES.includes(this.cvData.status)) {
        this.canEdit = true;
      }
    }

    if (this.activatedRoute.snapshot.data.roles[0] === TRAINEE_ROLE) {
      if (this.TRAINING_USER_EDIT_STATES.includes(this.cvData.status)) {
        this.canEdit = true;
      }
    }
  }

  private setCommentStatus() {
    if (SubmitConfirmDialogComponent) {
      this.canComment = this.activatedRoute.snapshot.data.roles[0] === TRAINING_ADMIN_ROLE && this.cvData.status === 'For Review';
    }
  }

  private populateResponse(response: ICvModel): void {
    this.cvData = response;
    this.setEditStatus();
  }

  private navigateToAdminSearch() {
    this.router.navigateByUrl('qa/portal/cv/admin/search');
  }

  private allDetailsEntered(): boolean {
    return !!this.allQualificationsCompleted(this.cvData.allQualifications) &&
      !!this.allWorkExperienceCompleted(this.cvData.allWorkExperience) &&
      !!this.cvData.hobbies &&
      !!this.cvData.profile;
  }

  private allQualificationsCompleted(qualifications: IQualification[]): boolean {
    return !!qualifications &&
      qualifications.length > 0 &&
      !qualifications.find(q => !this.qualificationCompleted(q));
  }

  private qualificationCompleted(qualifiation: IQualification): boolean {
    return !!qualifiation &&
      !!qualifiation.qualificationDetails;
  }

  private allWorkExperienceCompleted(workExperiences: IWorkExperience[]): boolean {
    return !!workExperiences &&
      workExperiences.length > 0 &&
      !workExperiences.find(w => !this.workExperienceCompleted(w));
  }

  private workExperienceCompleted(workExperience: IWorkExperience): boolean {
    return !!workExperience &&
      !!workExperience.start &&
      !!workExperience.jobTitle &&
      !!workExperience.workExperienceDetails;
  }
}
