import {Component, Input} from '@angular/core';
import { TraineeEvaluation } from '../models/trainee-evaluation-data';

@Component({
  selector: 'app-table-component',
  templateUrl: './table-component.component.html',
  styleUrls: ['./table-component.component.css']
})
export class TableComponentComponent {

  displayedColumns: string[] = ['traineeName', 'knowledge', 'recommend'];

  @Input() dataSource: TraineeEvaluation[];

  @Input() average: number;

  @Input() tqi: number;

  @Input() courseName: string;
}
