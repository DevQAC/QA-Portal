import {CohortCourseModel} from './cohort-course.model';
import {TrainerModel} from './trainer.model';

export class CohortModel {
  id: number;

  name: string;

  startDate: Date;

  trainerUserName: string;

  cohortCourses: CohortCourseModel[];
}
