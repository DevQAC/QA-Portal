import { IProfile } from './profile.model';
import { IHobbies } from './hobbies.model';
import { IWorkExperience } from './work-experience.model';
import { IQualification } from './qualification.model';
import { ISkills } from './skills.model';

export interface ICvModel {

    status: string;
    id: string;
    versionNumber: number;
    userName: string;
    firstName: string;
    surname: string;
    fullName: string;
    cohort: string;
    profile: IProfile;
    allSkills: ISkills[];
    allQualifications: IQualification[];
    allWorkExperience: IWorkExperience[];
    hobbies: IHobbies;
}


export const DEFAULT_CV: Partial<ICvModel> = {
    versionNumber: 1,
    profile: {
        profileDetails: '',
        profileFeedback: []
    },
    allSkills: [{
        programmingLanguages: [],
        ides: [],
        operatingSystems: [],
        devops: [],
        databases: [],
        platforms: [],
        other: []
    }],
    allQualifications: [],
    allWorkExperience: [],
    hobbies: {
        hobbiesDetails: '',
        hobbiesFeedback: []
    }
};
