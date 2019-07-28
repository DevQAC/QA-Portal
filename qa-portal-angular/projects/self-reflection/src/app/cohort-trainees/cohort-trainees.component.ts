import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import {Trainees} from './trainees';
import {QaCohortDto} from './models/QaCohortDto';
@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit {
  cohorts: any[]=[];
  trainees=Trainees;
  constructor(private http: HttpClient) {
    console.log(this.getCohorts());

    this.http.get<QaCohortDto[]>('https://localhost:8082/self-reflection-api/user/getCohorts/15').subscribe(data => console.log(data));

  } 

 // getTrainees() {
  //  return this.http.get('http://localhost:4200/assets/trainees.json');
 // }

 getCohorts(){
  return this.http.get('https://localhost:8082/core-api/applications/user/getCohorts/15').subscribe((data)=>console.log(data) );
 }
  ngOnInit() {
  }

}
