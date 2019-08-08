import {Component, Input, OnInit} from '@angular/core';
import {SelectedRatingModel} from './selected-rating.model';

@Component({
  selector: 'app-rated-question',
  templateUrl: './rated-question.component.html',
  styleUrls: ['./rated-question.component.css']
})
export class RatedQuestionComponent implements OnInit {

  @Input() options: number;

  @Input() questionText: string;

  @Input() selectedRating: SelectedRatingModel;

  @Input() isDisabled = false;

  optionsArr = [];

  constructor() { }

  ngOnInit() {
    for (let i = 0; i < this.options; i++) {
      this.optionsArr.push(i + 1);
    }
  }

  setModel(rating: number) {
    this.selectedRating.response = rating;
  }
}
