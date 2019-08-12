import { Component, OnInit } from '@angular/core';
import { FeedbackModel } from './models/feedback.model';
import { FormTypeService } from '../../_common/services/form-type.service';

@Component({
  selector: 'app-trainer-feedback-page',
  templateUrl: './trainer-feedback-page.component.html',
  styleUrls: ['./trainer-feedback-page.component.css']
})
export class TrainerFeedbackPageComponent implements OnInit {

  formInfo: FeedbackModel[] = [];

  constructor(private formTypeService: FormTypeService) { }

  ngOnInit() {
    this.formTypeService.getFormTypeTrainer().subscribe((response: FeedbackModel[]) => {
      this.formInfo = response;
      console.log(response);
    });
  }
}