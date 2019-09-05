import {Component, OnInit} from '@angular/core';

import {QaErrorHandlerService} from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';
import {RetrieveTrainerEvaluationHistoryService} from './services/retrieve-trainer-evaluation-history.service';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-trainer-evaluation-history',
  templateUrl: './trainer-evaluation-history.component.html',
  styleUrls: ['./trainer-evaluation-history.component.css']
})
export class TrainerEvaluationHistoryComponent implements OnInit {

  currentCourseDataSource: MatTableDataSource<any>;

  prevCoursesDataSource: MatTableDataSource<any>;

  currentCourse: any[] = [];

  prevCourses: any[] = [];


  constructor(private retrieveTrainerEvalHistory: RetrieveTrainerEvaluationHistoryService,
              private errorHandler: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.retrieveTrainerEvalHistory.getEvalHistory().subscribe(
      (response) => this.filterResults(response),
      (error) => this.errorHandler.handleError(error));
  }

  filterResults(data: any[]) {
    console.log('In filter resuits - num rows ' + data.length);
    let tempCurrent = [];
    let tempPrev = [];
    let count = 0;
    for (let course of data) {
      let startDate = new Date(course.startDate);
      const diffTime = Math.abs(new Date(/*'2019-07-02'*/).getTime() - startDate.getTime());
      let daysDiff = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;

      if (daysDiff > 0 && daysDiff < 7) {
        tempCurrent.push(course);
      } else {
        if (count < 8) {
          tempPrev.push(course);
          count++;
        }
      }
    }

    console.log('Current size ' + tempCurrent.length);
    console.log('Previous size ' + tempPrev.length);

    this.currentCourseDataSource = new MatTableDataSource<any>(tempCurrent);
    this.prevCoursesDataSource = new MatTableDataSource<any>(tempPrev);
  }
}
