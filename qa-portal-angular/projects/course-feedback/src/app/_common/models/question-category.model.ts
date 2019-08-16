import {ReturnedQuestionModel} from './returned-question.model';
import {QuestionModel} from './question.model';

export class QuestionCategoryModel {
  id: number;
  categoryName: string;
  hasComment: boolean;
  commentLabel: string;
  selectionType: string;
  displayDirection: string;
  questions: ReturnedQuestionModel[] = [];
}
