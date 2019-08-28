import { Component, OnInit, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { IFeedback } from '../_common/models/feedback.model';

import * as moment from 'moment';
import { FormControl, FormBuilder, FormGroup } from '@angular/forms';

import { MatDrawer } from '@angular/material/sidenav';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-cv-card-base',
  templateUrl: './cv-card-base.component.html',
  styleUrls: ['./cv-card-base.component.scss']
})
export class CvCardBaseComponent implements OnInit {
  @Input() title: string;
  @Input() canComment: boolean;
  @Input() feedback: IFeedback[];
  @Output() feedbackChange = new EventEmitter<IFeedback[]>();
  @Input() showOpenButton: boolean = true;
  @ViewChild('bottomScrollTarget', { static: true }) bottomScrollTarget: ElementRef;
  @ViewChild('drawer', { static: true }) public drawer: MatDrawer;

  public commentInput = new FormControl('');
  options: FormGroup;

  constructor(private keycloak: KeycloakService, fb: FormBuilder) {
    this.options = fb.group({
      hideRequired: true,
    });
  }

  ngOnInit() {
  }

  getFormattedDate(date: string): string {
    return moment(date).fromNow();
  }

  getFullDate(date: string): string {
    return moment(date).format('dddd, MMMM Do YYYY, h:mm:ss a');
  }


  addFeedbackItem() {
    const fb: IFeedback = {
      comment: this.commentInput.value,
      date: moment().format(),
      reviewer: this.keycloak.getUsername()
    };
    this.feedback.push(fb);
    this.feedbackChange.emit(this.feedback);
    this.commentInput.reset();

    setTimeout(() => {
      this.bottomScrollTarget.nativeElement.scrollIntoView(false);
    }, 0);
  }
}
