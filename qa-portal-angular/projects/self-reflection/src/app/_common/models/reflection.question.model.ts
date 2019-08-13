import { SelectedRatingModel } from '../../../../../qa-common/src/app/rated-question/selected-rating.model';
import {QuestionModel} from './question.model';

export class ReflectionQuestionModel implements SelectedRatingModel {
  selectedRating: number;

  id: number;

  reflectionId: number;

  question: QuestionModel;

  response: string;

  trainerResponse: number;
}
