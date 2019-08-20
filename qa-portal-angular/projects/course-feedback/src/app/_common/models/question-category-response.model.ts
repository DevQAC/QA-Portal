import {CommentModel} from './comment.model';
import {QuestionCategoryModel} from './question-category.model';
import {ReturnedQuestionModel} from './returned-question.model';

export class QuestionCategoryResponseModel {
  id: number;
  comment: CommentModel;
  questionCategory: QuestionCategoryModel;
  questionResponses: ReturnedQuestionModel[];
}
