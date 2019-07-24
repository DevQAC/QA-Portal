import { Component, OnInit } from '@angular/core';
import {RatedQuestionComponent} from './../../../../qa-common/src/app/rated-question/rated-question.component';
import {SelectedRatingModel} from './../../../../qa-common/src/app/rated-question/selected-rating.model';
import {SelectedRatingObject} from './models/SelectedRatingObject';
import { SelectionModel } from '@angular/cdk/collections';

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: './trainee-reflection.component.html',
  styleUrls: ['./trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit {
  questions: RatedQuestionComponent;
  rating:SelectedRatingModel;
  constructor() { }

  ngOnInit() {
    // this.questions = [
    //   {
    //     options:10,
    //     questionText:'hi',
    //     selectedRating:this.ngOnInit;
    //   }
    // ]
  }

}
