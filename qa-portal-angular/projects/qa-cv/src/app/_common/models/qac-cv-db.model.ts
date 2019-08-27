import { IProfile } from './profile.model';
import { IHobbies } from './hobbies.model';
import { IWorkExperience } from './work-experience.model';
import { IQualification } from './qualification.model';
import { ISkills } from './skills.model'


export interface ICvModel {
    version_number: string;
    status: string;
    id: number;
    user_name: string;
    first_name: string;
    surname: string;
    full_name: string;
    job_title: string;
    profile: IProfile;
    skills: ISkills;
    qualifications: IQualification[];
    work_experience: IWorkExperience[];
    hobbies: IHobbies;
}