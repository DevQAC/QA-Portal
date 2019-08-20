import {QuestionCategoryResponseModel} from './question-category-response.model';

export class FeedbackFormModel {
  id: number;

  cohortCourse: any;

  categoryResponses: QuestionCategoryResponseModel[];
}
