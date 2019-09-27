import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {QaCohortModel} from './models/qa-cohort.model';
import {TraineeModel} from './models/trainee.model';
import {Subscription, Observable} from 'rxjs';
import {CohortTraineesService} from './services/cohort-trainees.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit, OnDestroy {
  loadingCohorts = true;
  loadingTrainees = false;
  cohorts: QaCohortModel[];
  trainees: TraineeModel[];
  cohortColumns: string[] = ['name', 'startDate'];
  traineeColumns: string[] = ['userName', 'firstName', 'lastName'];
  subscriptionCohorts: Subscription;
  subscriptionTrainees: Subscription;
  cohortSelected = false;
  dataSource = new MatTableDataSource<QaCohortModel>(this.cohorts);
  dataSourceTrainees = new MatTableDataSource<TraineeModel>(this.trainees);

  constructor(private http: HttpClient,
              private errorHandler: QaErrorHandlerService,
              private cohortTraineesService: CohortTraineesService) {
  }

  ngOnInit() {
    this.subscriptionCohorts = this.cohortTraineesService.getCohorts()
      .subscribe((data) => {
          this.dataSource = new MatTableDataSource<QaCohortModel>(data);
          this.dataSourceTrainees = new MatTableDataSource<TraineeModel>([]);
          this.loadingCohorts = false;
        },
        (error) => {
          this.loadingCohorts = false;
          this.errorHandler.handleError(error);
        }
      );
  }

  updateTraineesTable(row) {
    this.cohortSelected = row.name;
    this.loadingTrainees = true;
    this.subscriptionTrainees = this.cohortTraineesService.getTraineesForReview(row.id).subscribe(
      (data) => {
        this.dataSourceTrainees = new MatTableDataSource<TraineeModel>(data);
        this.subscriptionTrainees.unsubscribe();
        this.loadingTrainees = false;
      },
      (error) => {
        this.loadingTrainees = false;
        this.errorHandler.handleError(error);
      }
    );
  }

  reviewReflectionForm(formId: string) {
    return '/qa/portal/training/self-reflection/trainer/trainee/' + formId;
  }

  ngOnDestroy(): void {
    this.subscriptionCohorts.unsubscribe();
  }
}
