import {CohortCourseModel} from '../../../../../portal-core/src/app/_common/models/cohort-course.model';

export class TrainerCourseHistoryModel {
  currentCohortCourse: CohortCourseModel;

  previousCohortCourses: CohortCourseModel[];

  cohortCourseHistory: CohortCourseModel[];

  averageKnowledgeRating: string;

  averageTqiRating: string;
}
