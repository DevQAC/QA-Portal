import { Component, OnInit } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Component({
  selector: 'app-cohort-trainees',
  templateUrl: './cohort-trainees.component.html',
  styleUrls: ['./cohort-trainees.component.css']
})
export class CohortTraineesComponent implements OnInit {

  trainees;
  constructor(private http: HttpClient) {
    this.http.get('http://localhost:4200/assets/trainees.json').subscribe(data => console.log(data));
    console.log('hi');
  }

  getTrainees() {
    return this.http.get('http://localhost:4200/assets/trainees.json');
  }
  ngOnInit() {
  }

}
