import { Trainee } from './trainee';
import { Trainer } from './trainer';

export class Cohort {
  id?: number;
  name: string;
  trainees: Trainee[];
  trainer: Trainer;
}
