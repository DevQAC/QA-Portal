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
  }

  setModel(rating: string) {
    this.selectedRating.response.push(rating);
  }

  matchedValue(entry: string, responseVal: string): boolean {
    const matched = !!responseVal && (entry === JSON.stringify(responseVal).trim());
    return matched;
  }
}
