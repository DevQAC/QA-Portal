import { QuestionModel } from './question.model';

export class DataModel {
    id : number;
    categoryName : string;
    hasComment: boolean;
    commentLabel: string;
    selectionType: string;
    displayDirection: string;
    questions: QuestionModel[] = [];

}
