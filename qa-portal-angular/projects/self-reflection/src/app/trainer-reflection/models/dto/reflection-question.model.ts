import {QuestionModel} from './question.model';
import {BaseModel} from './base.model';

export class ReflectionQuestionModel extends BaseModel {
  id?: number;
  reflectionId?: number;
  question: QuestionModel;
  response?: number;
  trainerResponse?: number;
}
