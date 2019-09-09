import {CohortCourseModel} from '../../../../../portal-core/src/app/_common/models/cohort-course.model';

export class TrainerCourseHistoryModel {
  currentCohortCourse: CohortCourseModel;

  previousCohortCourses: CohortCourseModel[];

  averageKnowledgeRating: string;

  averageTqiRating: string;
}
