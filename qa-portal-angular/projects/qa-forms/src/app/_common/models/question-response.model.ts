import { IComment } from './comment.model';
import { QuestionModel } from '../../../../../portal-core/src/app/_common/models/question.model';

export interface IQuestionResponse {
    id: number;
    comment: IComment;
    question: QuestionModel;
    responseValues: string[];
}
