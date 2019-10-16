import { QuestionCategoryModel } from '../../../../../portal-core/src/app/_common/models/question-category.model';
import { IQuestionResponse } from './question-response.model';
import {IComment} from './comment.model';

export interface ICategoryResponse {
    comment: IComment;
    id: number;
    parentId: number;
    questionCategory: QuestionCategoryModel;
    questionResponses: IQuestionResponse[];
}
