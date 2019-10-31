import { CourseModel } from './course.model';
import { TechnologyModel } from './technology.model';


export class CourseTechnologyModel {
    course: CourseModel;
    id: number;
    lastUpdatedBy: string;
    lastUpdatedTimestamp: Date;
    technology: TechnologyModel;
}