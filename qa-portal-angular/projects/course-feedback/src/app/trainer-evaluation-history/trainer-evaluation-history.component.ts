import { Component, OnInit } from '@angular/core';
import {RetrieveTrainerEvaluationHistoryService} from './services/retrieve-trainer-evaluation-history.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-trainer-evaluation-history',
  templateUrl: './trainer-evaluation-history.component.html',
  styleUrls: ['./trainer-evaluation-history.component.css']
})
export class TrainerEvaluationHistoryComponent implements OnInit {

  currentCourse: any[] = [];
  prevCourses: any[] = [];


  constructor(private retrieveTrainerEvalHistory: RetrieveTrainerEvaluationHistoryService,
    private errorHandler: QaErrorHandlerService) { }

  ngOnInit() {
    this.retrieveTrainerEvalHistory.getEvalHistory().subscribe( 
      (response) => this.filterResults(response),
      (error) => this.errorHandler.handleError(error));
  }

  filterResults(data: any[]) {
    let tempCurrent = [];
    let tempPrev = [];
    for(let course of data) {
      let startDate = new Date(course.startDate);
      const diffTime = Math.abs(new Date(/*'2019-07-02'*/).getTime() - startDate.getTime());
      let daysDiff = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
     
      //daysDiff between 1 week and 8 weeks
      if(daysDiff > 7 && daysDiff < 56) {
        tempPrev.push(course);
      } else if(daysDiff < 7) {
        tempCurrent.push(course);
      }
    }

    this.currentCourse = tempCurrent;
    this.prevCourses = tempPrev;
  }
}
