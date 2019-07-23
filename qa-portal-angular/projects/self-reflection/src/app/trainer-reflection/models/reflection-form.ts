import { Score } from './score';
import { UserScore } from './user-score';

export class ReflectionForm {
  id: number;
  userScores: UserScore[];
  text: string;
}
