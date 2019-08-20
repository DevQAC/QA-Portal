import {QuestionCategoryResponseModel} from './question-category-response.model';

export class EvaluationFormModel {
  id: number;

  // Cohort Course should not be amended, pass back to the service without amending it
  cohortCourse: any;

  // Trainee should not be amended, pass back to the service without amending it.
  trainee: any;

  categoryResponses: QuestionCategoryResponseModel[];
}
