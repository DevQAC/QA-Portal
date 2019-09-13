import {CourseModel} from './course.model';
import {TrainerModel} from './trainer.model';
import {CohortModel} from './cohort.model';
import {LocationModel} from './location.model';

export class CohortCourseModel {
  id: number;

  startDate: Date;

  endDate: Date;

  course: CourseModel;

  cohort: CohortModel;

  trainer: TrainerModel;

  location: LocationModel;

  feedbackStatus: string;

  averageKnowledgeRating: number;

  tqi: number;

  classSize: number;
}
