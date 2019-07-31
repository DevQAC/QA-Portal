import {QuestionModel} from './question.model';
import {SelectedRatingModel} from '../../../../../qa-common/src/app/rated-question/selected-rating.model';

export class ReflectionQuestionModel  implements SelectedRatingModel {
  id: number;

  question: QuestionModel;

  response: number;

  trainerResponse: number;
}
