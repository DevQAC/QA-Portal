import { QuestionModel } from './question.model';

export class DataModel {
    id : number;
    categoryName : string;
    hasComment: boolean;
    selectionType: string;
    displayDirection: string;
    question: QuestionModel[] = [];

}
