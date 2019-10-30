import {CourseModel} from './course.model';
import {TrainerModel} from './trainer.model';
import {CohortModel} from './cohort.model';
import {LocationModel} from './location.model';
import { UserModel } from './user.model';

export class CohortCourseModel {
  id: number;

  startDate: Date | string;

  endDate: Date | string;

  course: CourseModel;

  cohort: CohortModel;

  trainer: UserModel;

  location: LocationModel;

  feedbackStatus: string;

  averageKnowledgeRating: number;

  tqi: number;

  classSize: number;
}
