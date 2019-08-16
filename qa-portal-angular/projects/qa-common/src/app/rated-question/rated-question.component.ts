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

  constructor() {
  }

  ngOnInit() {
  }

  setModel(selection: string) {
    this.selectedRating.response = selection;
  }

  matchedValue(entry: string): boolean {
    return !!this.selectedRating.response && (entry === JSON.stringify(this.selectedRating.response).trim());
  }
}
