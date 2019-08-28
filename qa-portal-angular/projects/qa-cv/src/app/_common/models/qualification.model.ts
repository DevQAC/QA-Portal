import { IFeedback } from "./feedback.model";

export interface IQualification {
    qualificationDetails: string;
    qualificationFeedback: IFeedback[];
}