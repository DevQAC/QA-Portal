import {Component, Input, OnInit} from '@angular/core';
import {SelectedRatingModel} from './selected-rating.model';

@Component({
  selector: 'app-rated-question',
  templateUrl: './rated-question.component.html',
  styleUrls: ['./rated-question.component.css']
})
export class RatedQuestionComponent implements OnInit {

  @Input() options: string[];

  @Input() questionText: string;

  @Input() selectedRating: SelectedRatingModel;

  @Input() isDisabled = false;

  constructor() { }

  ngOnInit() {
    this.options.forEach(o => {
      console.log(o);
    });
  }

  setModel(rating: string) {
    this.selectedRating.response = rating;
  }

  matchedValue(entry: string, responseVal: string): boolean {
    const matched = (entry === responseVal);
    console.log('Matched ' + matched);
    return matched;
  }
}
