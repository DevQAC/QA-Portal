import { IComment } from './comment.model';
import { IQuestion } from './question.model';

export interface IQuestionResponse<ResponseType = any> {
    id: number;
    comment: IComment;
    question: IQuestion;
    responseValues: ResponseType;
}
