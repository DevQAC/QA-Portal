import { IFeedback } from "./feedback.model";

export interface IProfile {
    p_detail: string;
    feedback: IFeedback[];
}