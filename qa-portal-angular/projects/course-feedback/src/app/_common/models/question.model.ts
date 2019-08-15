import { SelectedRatingModel } from 'projects/qa-common/src/app/rated-question/selected-rating.model';
import { CheckBoxResponseModel } from './checkbox-response.model';

export class QuestionModel {
    id: number;
    body: string;
    hasComment: boolean;
    commentLabel: string;
    selectionOptionsJson: string;
    selectionOptionsList: string[];
    selectionOptions_PLACEHOLDER: any;
}