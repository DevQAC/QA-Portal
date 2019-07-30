import {ReflectionQuestionModel} from './reflection.question.model';

export class SelfReflectionFormModel {
  id: number;

  // qaUser: any;
  //
  // qaUserSelfReflectionFormStatuses: SelfReflectionFormStatus[];
  //
  // selfReflectionReviews: any;

  reflectionQuestions: ReflectionQuestionModel[] = [];

  strengths: string;

  weaknesses: string;

  opportunities: string;

  threats: string;

  formDate: Date;

  currentStatus: string;
}
