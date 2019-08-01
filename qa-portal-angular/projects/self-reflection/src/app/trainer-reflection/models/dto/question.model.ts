import { ReflectionModel } from './reflection.model';
import { BaseModel } from './base.model';
import { ReflectionQuestionModel } from './reflection-question.model';

export class QuestionModel extends BaseModel {
  id?: number;
  body?: string;
  numOptions: number;
  category?: string;
  forms?: ReflectionQuestionModel[];
}
