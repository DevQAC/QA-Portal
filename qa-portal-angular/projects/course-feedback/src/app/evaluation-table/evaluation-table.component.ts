import { Component, OnInit } from '@angular/core';

export interface CourseEvaluation {
  course: string;
  start: string;
  end: string;
  courseid: string;
  pin: number;
  status: string;
}

const ELEMENT_DATA: CourseEvaluation[] = [
  {course: 'Test1', start: '01/01/19', end: '08/01/19', courseid: 'HAPHAP', pin: 4658554, status: "\u2714"},
  {course: 'Test2', start: '08/01/19', end: '15/01/19', courseid: 'HAPHAP', pin: 4658554, status: "\u274C"},
  {course: 'Test3', start: '08/01/19', end: '15/01/19', courseid: 'HAPHAP', pin: 4658554, status: "\u{1F512}"},
];

@Component({
  selector: 'app-evaluation-table',
  templateUrl: './evaluation-table.component.html',
  styleUrls: ['./evaluation-table.component.css']
})
export class EvaluationTableComponent implements OnInit {

  constructor() { }

  ngOnInit() {

    //call service here
  }

  displayedColumns: string[] = ['course', 'start', 'end', 'courseid', 'pin', 'status'];
  dataSource = ELEMENT_DATA;

}