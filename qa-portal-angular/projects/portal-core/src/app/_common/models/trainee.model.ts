import {UserModel} from './user.model';
import {CohortModel} from './cohort.model';

export class TraineeModel extends UserModel  {
  cohort: CohortModel;
}
