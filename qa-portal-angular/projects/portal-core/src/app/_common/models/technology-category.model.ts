import { TechnologyModel } from './technology.model';

export class TechnologyCategoryModel {
    id: number;
    categoryName: string;
    lastUpdatedBy: string;
    lastUpdatedTimestamp: Date;
    searchString: string;
    technologies: TechnologyModel[];
}
