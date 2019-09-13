import {SelectedRatingModel} from '../../../../../qa-common/src/app/rated-question/selected-rating.model';
import {QuestionModel} from './question.model';

export class ReturnedQuestionModel<ResponseType = string> implements IGenericQuestionResponse<ResponseType> {
  id: number;
  question: QuestionModel;
  comment: string;
  responseValues: string[];
  response: ResponseType;
}

export interface IGenericQuestionResponse<ResponseType> {
  response: ResponseType;
}
