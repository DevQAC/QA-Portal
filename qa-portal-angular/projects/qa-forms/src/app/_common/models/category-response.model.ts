import { IQuestionCategory } from './question-category.model';
import { IQuestionResponse } from './question-response.model';
import {IComment} from './comment.model';

export interface ICategoryResponse {
    comment: IComment;
    id: number;
    parentId: number;
    questionCategory: IQuestionCategory;
    questionResponses: IQuestionResponse[];
}
