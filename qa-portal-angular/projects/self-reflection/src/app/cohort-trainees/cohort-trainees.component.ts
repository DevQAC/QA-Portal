import { Component, OnDestroy, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { QaCohortModel } from './models/qa-cohort.model';
import { TraineeModel } from './models/trainee.model';
import { Subscription, Observable } from 'rxjs';
import { CohortTraineesService } from './services/cohort-trainees.service'
import { mergeMap } from 'rxjs/operators';

@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit, OnDestroy {
  singleCohort: Observable<{}>;
  cohorts: QaCohortModel[];
  trainees: TraineeModel[];
  subscriptionCohorts: Subscription;
  subscriptionTrainees: Subscription;
  currentTraineeID = 1;
  selectedCohort = 1;
  panelTraineesOpenState = false;
  panelCohortOpenState = true;
  test;

  constructor(private http: HttpClient, private cohortTraineesService: CohortTraineesService) {
  }

  ngOnInit() {
    this.subscriptionCohorts = this.cohortTraineesService.getCohorts().subscribe((data) => this.cohorts = data
    );
    this.cohortTraineesService.getCohorts().pipe(
      mergeMap(data => this.test=this.cohortTraineesService.getTrainees(data[0].getID()))
    ).subscribe(data=> data);
    this.subscriptionTrainees = this.cohortTraineesService.getTrainees(this.selectedCohort).subscribe(
      (data) => this.trainees = data
    );

  }

  ngOnDestroy(): void {
    this.subscriptionCohorts.unsubscribe();
    this.subscriptionTrainees.unsubscribe();
  }
}
