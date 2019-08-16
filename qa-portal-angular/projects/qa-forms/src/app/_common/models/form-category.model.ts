import { IGenericQuestion } from './generic-question.model';
import { SelectionTypes } from '../types/selection.type';

export interface ICategory {
    categoryName: string;
    questions: IGenericQuestion<any>[];
    selectionType: SelectionTypes;
}