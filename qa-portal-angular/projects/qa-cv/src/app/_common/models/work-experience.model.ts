import { IFeedback } from "./feedback.model";

export interface IWorkExperience {
    jobTitle: string;
    start: string;
    end: string;
    workExperienceDetails: string;
    workExperienceFeedback: IFeedback[];
}