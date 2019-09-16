import { Component, Input, Output, EventEmitter, ViewChild, ElementRef } from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MatDrawer } from '@angular/material/sidenav';
import { KeycloakService } from 'keycloak-angular';
import { IFeedback } from '../_common/models/feedback.model';
import * as moment from 'moment';

@Component({
  selector: 'app-cv-card-base',
  templateUrl: './cv-card-base.component.html',
  styleUrls: ['./cv-card-base.component.scss']
})
export class CvCardBaseComponent {
  @Input() cardTitle: string;
  @Input() canComment: boolean;
  @Input() canEdit: boolean;
  @Input() feedback: IFeedback[];
  @Output() feedbackChange = new EventEmitter<IFeedback[]>();
  @Input() showOpenButton: boolean = true;
  @ViewChild('commentContainer', { static: true }) commentContainer: ElementRef;
  @ViewChild('drawer', { static: true }) public drawer: MatDrawer;

  public commentInput = new FormControl('', Validators.required);
  public options: FormGroup;

  constructor(private keycloak: KeycloakService, fb: FormBuilder) {
    this.options = fb.group({
      hideRequired: true,
    });
  }

  getFormattedDate(date: string): string {
    return moment(date).fromNow();
  }

  getFullDate(date: string): string {
    return moment(date).format('dddd, MMMM Do YYYY, h:mm:ss a');
  }

  scrollCommentsToBottom(): void {
    const { SimpleBar } = this.commentContainer.nativeElement;
    SimpleBar.getScrollElement().scrollTo(0, SimpleBar.contentEl.clientHeight);
  }


  addFeedbackItem(): void {
    if (this.commentInput.valid) {
      const fb: IFeedback = {
        comment: this.commentInput.value,
        date: moment().format(),
        reviewer: this.keycloak.getUsername()
      };
      this.feedback.push(fb);
      this.feedbackChange.emit(this.feedback);
      this.commentInput.reset();
      this.commentInput.markAsUntouched();

      setTimeout(() => {
        this.scrollCommentsToBottom();
      }, 0);
    }
  }
}
