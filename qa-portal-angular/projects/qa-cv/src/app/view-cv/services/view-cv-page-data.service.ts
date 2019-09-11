import {Injectable} from '@angular/core';
import {UserSkillsModel} from '../../_common/models/user-skills.model';
import {DEFAULT_CV, ICvModel} from '../../_common/models/qac-cv-db.model';
import {IN_PROGRESS_STATUS} from '../models/view-cv.constants';

@Injectable()
export class ViewCvPageDataService {
  constructor() {
  }

  public initialiseNewCv(cvData: ICvModel, useExistingCvAsTemplate: boolean): ICvModel {
    const tempCv = cvData;
    const emptyCv = null;
    let newCv = null;
    if (!!useExistingCvAsTemplate) {
      newCv = {...DEFAULT_CV, ...cvData};
      newCv.id = null;
      newCv.versionNumber = null;
    } else {
      newCv = {...DEFAULT_CV, ...emptyCv};
      newCv.allSkills = tempCv.allSkills;
      newCv.fullName = tempCv.fullName;
    }
    newCv.status = IN_PROGRESS_STATUS;
    return newCv;
  }

  public populateCvUserDetails(cvData: ICvModel, userSkillsModel: UserSkillsModel): void {
    cvData.userName = userSkillsModel.userName;
    cvData.firstName = userSkillsModel.userFirstName;
    cvData.surname = userSkillsModel.userLastName;
    cvData.fullName = cvData.firstName + ' ' + cvData.surname;
    cvData.status = IN_PROGRESS_STATUS;
  }

  public populateCvSkills(cvData: ICvModel, userSkillsModel: UserSkillsModel): void {
    Object.keys(cvData.allSkills[0]).forEach((skillCategory) => {
      cvData.allSkills[0][skillCategory] = this.getSkillsArrayForTechnology(userSkillsModel.skills[skillCategory]);
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
}
