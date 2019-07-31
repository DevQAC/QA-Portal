import { RatedQuestionModel } from './rated-question.model';
import { SelectedRatingModel } from '../../../../../qa-common/src/app/rated-question/selected-rating.model';

export class SelfRatingModel implements SelectedRatingModel {
    id: number;

    selfRatingQuestion: RatedQuestionModel;

    response: number;
}
