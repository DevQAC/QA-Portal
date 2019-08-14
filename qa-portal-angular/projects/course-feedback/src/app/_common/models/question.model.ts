import { SelectedRatingModel } from 'projects/qa-common/src/app/rated-question/selected-rating.model';
import { CheckBoxResponseModel } from './checkbox-response.model';

export class QuestionModel implements SelectedRatingModel, CheckBoxResponseModel {
    id: number;
    body: string;
    hasComment: boolean;
    commentLabel: string;
    selectionOptionsJson: string;
    selectionOptionsList: string[];
    response: string;
    responseCheck: string[];
}