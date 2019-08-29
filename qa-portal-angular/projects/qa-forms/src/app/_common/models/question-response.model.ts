import { IComment } from './comment.model';
import { IQuestion } from './question.model';

export interface IQuestionResponse {
    id: number;
    comment: IComment;
    question: IQuestion;
    responseValues: string[];
}
