import { ReturnedQuestionModel } from "./returned-question.model";
import { QuestionModel } from './question.model';

export class DataModel {
    id : number;
    categoryName : string;
    hasComment: boolean;
    commentLabel: string;
    selectionType: string;
    displayDirection: string;
    questions: ReturnedQuestionModel[] = [];

}
