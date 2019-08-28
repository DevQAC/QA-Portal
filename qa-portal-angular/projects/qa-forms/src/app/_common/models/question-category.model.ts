import { IQuestion } from './question.model';

export interface IQuestionCategory {
    categoryName: string;
    commentLabel: string;
    displayDirection: string;
    hasComment: boolean;
    id: number;
    lastUpdatedBy: string;
    lastUpdatedTimestamp: string;
    questions: IQuestion[];
    selectionType: string;
}