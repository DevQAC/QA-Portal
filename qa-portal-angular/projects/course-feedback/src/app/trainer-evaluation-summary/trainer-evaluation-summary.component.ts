import { Component, OnInit } from '@angular/core';
import {TrainerEvaluationServicesService} from './services/trainer-evaluation-services.service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {Subscription} from 'rxjs';
import {MatTableDataSource} from '@angular/material';

@Component({
  selector: 'app-trainer-evaluation-summary',
  templateUrl: './trainer-evaluation-summary.component.html',
  styleUrls: ['./trainer-evaluation-summary.component.css']
})

export class TrainerEvaluationSummaryComponent implements OnInit {

  
  constructor(private trainerEvaluationServices: TrainerEvaluationServicesService,
    private errorHandlerService: QaErrorHandlerService) {
}

ngOnInit() {
  this.trainerEvaluationServices.getTrainerEvaluationSummary().subscribe((response) => {
      this.viewModel = response;
      this.dataSource = new MatTableDataSource<trainerEvaluationSummaryRowModel>(this.viewModel.evaluationSummaryRows);
      this.dataLoading = false;
    },
    (error) => {
      this.dataLoading = false;
      this.errorHandlerService.handleError(error);
    });

    
}
getEvaluationUrl(cohortCourseId: string): string {
  return '/qa/portal/training/feedback/trainer/evaluation/' + cohortCourseId;
}


}




