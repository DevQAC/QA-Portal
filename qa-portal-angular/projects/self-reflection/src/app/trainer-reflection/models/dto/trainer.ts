import { Cohort } from './cohort';
import { User } from './user';

export class Trainer extends User {
  cohorts?: Cohort[];
}
