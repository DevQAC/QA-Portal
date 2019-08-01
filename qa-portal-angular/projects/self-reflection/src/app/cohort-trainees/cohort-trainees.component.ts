import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {QaCohortModel} from './models/qa-cohort.model';
import {TraineeModel} from './models/trainee.model';
import {Subscription, Observable} from 'rxjs';
import {CohortTraineesService} from './services/cohort-trainees.service';
import {MatTableDataSource} from '@angular/material/table';
import {MatPaginator} from '@angular/material/paginator';

@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit, OnDestroy {
  cohorts: QaCohortModel[];
  trainees: TraineeModel[];
  cohortColumns: string[] = ['name', 'startDate'];
  traineeColumns: string[] = ['userName', 'firstName', 'lastName'];
  subscriptionCohorts: Subscription;
  subscriptionTrainees: Subscription;
  cohortSelected = false;
  dataSource = new MatTableDataSource<QaCohortModel>(this.cohorts);
  dataSourceTrainees = new MatTableDataSource<TraineeModel>(this.trainees);

  constructor(private http: HttpClient, private cohortTraineesService: CohortTraineesService) {
  }

  ngOnInit() {
    this.subscriptionCohorts = this.cohortTraineesService.getCohorts()
      .subscribe((data) => {
          this.dataSource = new MatTableDataSource<QaCohortModel>(data);
          this.dataSourceTrainees = new MatTableDataSource<TraineeModel>([]);
        }
      );
  }

  updateTraineesTable(row) {
    this.cohortSelected = row.name;
    this.subscriptionTrainees = this.cohortTraineesService.getTrainees(row.id).subscribe(
      (data) => {
        console.log('In update trainees table');
        this.dataSourceTrainees = new MatTableDataSource<TraineeModel>(data);
        this.subscriptionTrainees.unsubscribe();
      }
    );
  }

  reviewReflectionForm(formId: string) {
    return '/qa/portal/training/trainer/selfreflection/' + formId;
  }

  ngOnDestroy(): void {
    this.subscriptionCohorts.unsubscribe();
  }
}
