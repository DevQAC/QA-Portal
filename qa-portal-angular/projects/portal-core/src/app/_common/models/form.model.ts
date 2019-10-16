import { QuestionCategoryModel } from './question-category.model';

export class FormModel {
    id: number;
    formName: string;
    description: string;
    lastUpdatedBy: string;
    lastUpdatedTimestamp: Date;
    questionCategories: QuestionCategoryModel[];
}
