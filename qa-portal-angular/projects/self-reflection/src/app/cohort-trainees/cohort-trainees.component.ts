import {Component, OnDestroy, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Trainees} from './trainees';
import {QaCohortModel} from './models/qa-cohort.model';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit, OnDestroy {
  cohorts: any[] = [];
  trainees = Trainees;
  subscription: Subscription;

  constructor(private http: HttpClient) {
  }

  ngOnInit() {
    this.subscription = this.getCohorts();

    this.http.get<QaCohortModel[]>('self-reflection-api/user/getCohorts/15').subscribe(
      data => console.log(data)
    );
  }

  ngOnDestroy(): void {
    this.subscription.unsubscribe();
  }

  getCohorts() {
    return this.http.get('core-api/applications/user/getCohorts/15').subscribe(
      (data) => console.log(data)
    );
  }

  // getTrainees() {
  //  return this.http.get('assets/trainees.json');
  // }
}
