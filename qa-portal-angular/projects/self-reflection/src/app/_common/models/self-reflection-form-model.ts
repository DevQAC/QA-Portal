import {ReflectionQuestionModel} from './reflection.question.model';

export class SelfReflectionFormModel {
  id: number;

  reflectionQuestions: ReflectionQuestionModel[] = [];

  strengths: string;

  weaknesses: string;

  opportunities: string;

  threats: string;

  formDate: Date;

  status: string;

}
