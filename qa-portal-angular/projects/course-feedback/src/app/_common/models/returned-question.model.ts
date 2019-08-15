import { SelectedRatingModel } from '../../../../../qa-common/src/app/rated-question/selected-rating.model';
import { QuestionModel } from './question.model';

export class  ReturnedQuestionModel implements SelectedRatingModel  {
    id: number;
    question: QuestionModel;
    comment: string;
    response: string;

}
