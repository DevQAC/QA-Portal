import { IFeedback } from "./feedback.model";

export interface IProfile {
    profileDetails: string;
    profileFeedback: IFeedback[];
}