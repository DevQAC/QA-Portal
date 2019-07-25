import { Component, OnInit } from '@angular/core';
import {RatedQuestionComponent} from './../../../../qa-common/src/app/rated-question/rated-question.component';
import {SelectedRatingModel} from './../../../../qa-common/src/app/rated-question/selected-rating.model';
import {SelectedRatingObject} from './models/SelectedRatingObject';
import { SelectionModel } from '@angular/cdk/collections';
import { QuestionsServiceService } from './services/questions-service.service'
import { SelfReflectionFormViewModel } from './models/self-reflection-form-vmodel';
import { RatedQuestionsService } from './services/rated-questions.service';
import { SelfReflectionFormService } from './services/self-reflection-form.service';

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: './trainee-reflection.component.html',
  styleUrls: ['./trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit {

  selfReflectionViewModel = new SelfReflectionFormViewModel();
  
  constructor(private ratedQuestionsService: RatedQuestionsService,
    private selfReflectionFormService: SelfReflectionFormService) {
  }

  ngOnInit() {
    
  }

}
