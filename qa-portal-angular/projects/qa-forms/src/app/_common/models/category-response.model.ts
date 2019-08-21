import { IQuestionCategory } from './question-category.model';
import { IQuestionResponse } from './question-response.model';

export interface ICategoryResponse {
    comment: string;
    id: number;
    parentId: number;
    questionCategory: IQuestionCategory;
    questionResponses: IQuestionResponse[];
}