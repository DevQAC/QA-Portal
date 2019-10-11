import { TechnologyModel } from './technology.model';

export class CourseModel {
  id: number;

  courseName: string;

  courseCode: string;

  duration: number;

  courseTechnologies: TechnologyModel[];
}
