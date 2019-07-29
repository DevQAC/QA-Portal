import { Base } from './base';
import { Question } from './question';

export class CohortQuestion extends Base {
  id?: number;
  cohortId: number;
  questionId: number;
}
