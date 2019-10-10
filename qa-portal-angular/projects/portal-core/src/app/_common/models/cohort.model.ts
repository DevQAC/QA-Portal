import { CohortCourseModel } from './cohort-course.model';

export class CohortModel {
  id: number;

  name: string;

  startDate: string;

  trainerUserName: string;

  traineeNames: string[];

  cohortCourses: CohortCourseModel[];
}
