import {Component} from '@angular/core';

export interface PeriodicElement {
  traineeName: String;
  question1: number;
  question2: number;
  question3: number;
  question4: number;
  question5: number;
  question6: number;
  question7: number;
  question8: number;
  question9: number;
  question10: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {traineeName: "Joe Bloggs", question1: 1, question2: 8, question3: 1, question4: 10, question5: 6, question6: 3, question7: 9, question8: 9, question9: 6, question10: 7}, 
  {traineeName: "Bill Gates", question1: 1, question2: 8, question3: 1, question4: 10, question5: 6, question6: 3, question7: 9, question8: 9, question9: 6, question10: 7},
  {traineeName: "Steve Jobs", question1: 1, question2: 8, question3: 1, question4: 10, question5: 6, question6: 3, question7: 9, question8: 9, question9: 6, question10: 7}, 
];

@Component({
  selector: 'app-table-component',
  templateUrl: './table-component.component.html',
  styleUrls: ['./table-component.component.css']
})
export class TableComponentComponent {

  displayedColumns: string[] = ['traineeName', 'question1', 'question2', 'question3', 'question4', 'question5', 'question6', 'question7', 'question8', 'question9', 'question10'];
  dataSource = ELEMENT_DATA;

}
