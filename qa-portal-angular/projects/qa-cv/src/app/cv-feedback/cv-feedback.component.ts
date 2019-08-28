import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IFeedback } from '../_common/models/feedback.model';

@Component({
  selector: 'app-cv-feedback',
  templateUrl: './cv-feedback.component.html',
  styleUrls: ['./cv-feedback.component.css']
})
export class CvFeedbackComponent implements OnInit {
  @Input() feedback: IFeedback;
  @Output() feedbackChange = new EventEmitter<IFeedback>();

  constructor() { }

  ngOnInit() {
  }

  onInputChange(data) {
    this.feedback.comment = data;
    this.feedbackChange.emit(this.feedback);
  }

}
