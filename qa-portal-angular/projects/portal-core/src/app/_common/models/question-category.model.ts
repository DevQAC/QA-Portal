import { QuestionModel } from './question.model';


export class QuestionCategoryModel {
    categoryName: string;
    commentLabel: string;
    displayDirection: string;
    hasComment = false;
    id: number;
    lastUpdatedBy: string;
    lastUpdatedTimestamp: string;
    questions: QuestionModel[] = [];
    selectionType: string;
}
