import { User } from './user';
import { Cohort } from './cohort';

export class Trainee extends User {
  cohort: Cohort;
}
