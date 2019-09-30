import {CohortCourseModel} from './cohort-course.model';

export class CohortModel {
  id: number;

  name: string;

  startDate: string;

  cohortCourses: CohortCourseModel[];
}
