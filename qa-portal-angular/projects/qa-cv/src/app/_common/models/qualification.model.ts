import { IFeedback } from "./feedback.model";

export interface IQualification {
    q_detail: string;
    feedback: IFeedback[];
}