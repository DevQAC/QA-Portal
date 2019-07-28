import { Component, OnInit } from '@angular/core';
import {Subscription} from 'rxjs';
import {TrainerCohortsService} from '/Users/hamzaar/Documents/workspace/QA-Portal/qa-portal-angular/projects/portal-core/src/app/_common/services/trainer-cohorts-service/trainer-cohorts.service';


export interface PeriodicElement {
  name: string;
  position: number;
  date: number;
}

const ELEMENT_DATA: PeriodicElement[] = [
  {position: 1, name: 'Hydrogen', date: 1.0079},
  {position: 2, name: 'Helium', date: 4.0026},
  {position: 3, name: 'Lithium', date: 6.941},
  {position: 4, name: 'Beryllium', date: 9.0122},
  {position: 5, name: 'Boron', date: 10.811},
  {position: 6, name: 'Carbon', date: 12.0107},
  {position: 7, name: 'Nitrogen', date: 14.0067},
  {position: 8, name: 'Oxygen', date: 15.9994},
  {position: 9, name: 'Fluorine', date: 18.9984},
  {position: 10, name: 'Neon', date: 20.1797},
];

@Component({
  selector: 'app-trainer-cohorts',
  templateUrl: './trainer-cohorts.component.html',
  styleUrls: ['./trainer-cohorts.component.css']
})
export class TrainerCohortsComponent implements OnInit {
    displayedColumns: string[] = ['position', 'name', 'date'];
    dataSource = ELEMENT_DATA;

    trainerCohortsSubscription: Subscription;

  constructor(private trainerCohortsService: TrainerCohortsService) { }

  ngOnInit() {
    this.trainerCohortsSubscription = this.trainerCohortsService.getTrainerCohorts()
      .subscribe((response) => {
          console.log(response);
          //this.dataSource = response;
        }
      );
  }

  ngOnDestroy(): void {
    this.trainerCohortsSubscription.unsubscribe();
  }

}
