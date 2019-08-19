import { IGenericQuestion } from './generic-question.model';
import { ControlTypes } from '../types/control.types';

export interface ICategory {
    categoryName: string;
    questions: IGenericQuestion<any>[];
    selectionType: ControlTypes;
}