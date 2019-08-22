import { IFeedback } from "./feedback.model";

export interface IWorkExperience {
    job: string;
    start_date: string;
    end_date: string;
    detail: string;
    feedback: IFeedback[];
}