import {Component, OnInit} from '@angular/core';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-trainer-feedback-history',
  templateUrl: './trainer-feedback-history.component.html',
  styleUrls: ['./trainer-feedback-history.component.css']
})
export class TrainerFeedbackHistoryComponent implements OnInit {

  dataLoading = true;

  displayedColumns: string[] = ['course', 'start', 'end', 'feedback-status'];

  dataSource: MatTableDataSource<any>;

  constructor() { }

  ngOnInit() {
  }

}
