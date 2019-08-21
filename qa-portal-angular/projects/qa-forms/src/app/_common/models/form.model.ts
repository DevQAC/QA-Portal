import { ICategoryResponse } from './category-response.model';

export interface IFormModel {
    categoryResponses: ICategoryResponse[];
    
    readonly id: number;
    readonly cohortCourse: any;
    readonly trainee: any;
}