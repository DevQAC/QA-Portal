import { EvaluationTableRow } from './evaluation-table-row';
import { EvaluationTableRow2 } from './evaluation-table-row2';

export class TrainerEvaluationViewModel {
    evaluationCols = ['c1', 'c2', 'c3', 'c4', 'c5'];

    tableRows: EvaluationTableRow[] = [];
}

export class TrainerEvaluationViewModel2 {
    evaluationCols = ['c1', 'c2'];

    tableRows: EvaluationTableRow2[] = [];
}

