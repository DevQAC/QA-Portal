import { Component, OnInit, Input } from '@angular/core';
import { QuestionModel } from '../../_common/models/question.model';
import { SelectedRatingModel } from '../../../../../qa-common/src/app/rated-question/selected-rating.model';


@Component({
  selector: 'app-feedback-question',
  templateUrl: './feedback-question.component.html',
  styleUrls: ['./feedback-question.component.css']
})
export class FeedbackQuestionComponent implements OnInit {

  @Input() value : QuestionModel;

  @Input() selectedRating: SelectedRatingModel;


  constructor() { }

  ngOnInit() { }

  setModel(rating: string) {
    //this.selectedRating.response = rating;
    
    console.log("New Value = " + this.selectedRating.response);
  }
  
}
