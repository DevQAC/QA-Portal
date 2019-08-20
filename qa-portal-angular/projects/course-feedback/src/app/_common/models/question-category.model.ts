import {ReturnedQuestionModel} from './returned-question.model';
import {QuestionModel} from './question.model';
import {ICategory} from '../../../../../qa-forms/src/app/_common/models/form-category.model';
import {ControlTypes} from '../../../../../qa-forms/src/app/_common/types/control.types';

export class QuestionCategoryModel implements ICategory {
  id: number;
  categoryName: string;
  hasComment: boolean;
  commentLabel: string;
  selectionType: ControlTypes;
  displayDirection: string;
  questions: any[];
}
