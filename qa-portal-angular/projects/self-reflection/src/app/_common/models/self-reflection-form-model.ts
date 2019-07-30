import {RatedQuestionModel} from './rated-question.model';
import {SelfReflectionFormStatus} from './self-reflection-form-status';
import {SelfRatingModel} from './self-rating.model';

export class SelfReflectionFormModel {
  id: number;

  qaUser: any;

  qaUserSelfReflectionFormStatuses: SelfReflectionFormStatus[];

  selfReflectionReviews: any;

  selfRatings: SelfRatingModel[] = [];

  strengthsText: string;

  weaknessesText: string;

  opportunitiesText: string;

  threatsText: string;

  weekCommencing: Date;

  currentStatus: string;
}
