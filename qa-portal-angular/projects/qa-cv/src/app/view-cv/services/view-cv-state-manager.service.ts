import {Injectable} from '@angular/core';
import {TRAINEE_ROLE, TRAINING_ADMIN_ROLE} from '../../../../../portal-core/src/app/_common/models/portal-constants';
import {ActivatedRoute} from '@angular/router';
import {ICvModel} from '../../_common/models/qac-cv-db.model';
import {IQualification} from '../../_common/models/qualification.model';
import {IWorkExperience} from '../../_common/models/work-experience.model';
import {ADMIN_USER_EDIT_STATES, TRAINING_USER_EDIT_STATES} from '../models/view-cv.constants';


@Injectable()
export class ViewCvStateManagerService {

  constructor() {
  }

  public isPageEditable(activatedRoute: ActivatedRoute, cvData: ICvModel): boolean {
    let isEditable = false;
    if (activatedRoute.snapshot.data.roles[0] === TRAINING_ADMIN_ROLE) {
      if (ADMIN_USER_EDIT_STATES.includes(cvData.status)) {
        isEditable = true;
      }
    }

    if (activatedRoute.snapshot.data.roles[0] === TRAINEE_ROLE) {
      if (TRAINING_USER_EDIT_STATES.includes(cvData.status)) {
        isEditable = true;
      }
    }
    return isEditable;
  }

  public isPageDisplayForTrainee(activatedRoute: ActivatedRoute) {
    if (activatedRoute.snapshot.data.roles[0] === TRAINING_ADMIN_ROLE) {
      return false;
    }
    return true;
  }

  public isMandatoryCvDetailsEntered(cvData: ICvModel): boolean {
    return !!this.allQualificationsCompleted(cvData.allQualifications) &&
      !!this.allWorkExperienceCompleted(cvData.allWorkExperience) &&
      !!cvData.hobbies && !!cvData.hobbies.hobbiesDetails &&
      !!cvData.profile && !!cvData.profile.profileDetails;
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
