import { Component, OnDestroy, OnInit, ViewChild } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QaCohortModel } from './models/qa-cohort.model';
import { TraineeModel } from './models/trainee.model';
import { Subscription, Observable } from 'rxjs';
import { CohortTraineesService } from './services/cohort-trainees.service'
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';

@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit, OnDestroy {
  cohorts: QaCohortModel[];
  trainees: TraineeModel[];
  displayedColumns: string[] = ['name', 'id'];
  displayedColumnsTrainees: string[] = ['id', 'userName', 'firstName', 'lastName'];


  subscriptionCohorts: Subscription;
  subscriptionTrainees: Subscription;
  panelTraineesOpenState = false;
  panelCohortOpenState = true;
  dataSource = new MatTableDataSource<QaCohortModel>(this.cohorts);
  dataSourceTrainees = new MatTableDataSource<TraineeModel>(this.trainees);

  constructor(private http: HttpClient, private cohortTraineesService: CohortTraineesService) {
  }

  ngOnInit() {
    this.subscriptionTrainees = this.cohortTraineesService.getTrainees(1).subscribe(
      (data) => this.dataSourceTrainees = new MatTableDataSource<TraineeModel>(data)
    );

    this.subscriptionCohorts = this.cohortTraineesService.getCohorts()
      .subscribe((data) => this.dataSource = new MatTableDataSource<QaCohortModel>(data)
      );
  }

  updateTraineesTable(input) {
    this.subscriptionTrainees = this.cohortTraineesService.getTrainees(input).subscribe(
      (data) => this.dataSourceTrainees = new MatTableDataSource<TraineeModel>(data)
    );
  }

  ngOnDestroy(): void {
    this.subscriptionCohorts.unsubscribe();
    this.subscriptionTrainees.unsubscribe();
  }
}
