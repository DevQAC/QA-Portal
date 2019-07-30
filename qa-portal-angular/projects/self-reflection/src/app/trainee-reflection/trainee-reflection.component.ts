import {Component, OnDestroy, OnInit} from '@angular/core';
import {QuestionsServiceService} from './services/questions-service.service';
import {SelfReflectionFormViewModel} from './models/self-reflection-form-vmodel';
import {RatedQuestionsService} from './services/rated-questions.service';
import {SelfReflectionFormService} from './services/self-reflection-form.service';
import {Subscription} from 'rxjs';
import {SelfReflectionFormModel} from "../_common/models/self-reflection-form-model";
import {SelfRatingModel} from "../_common/models/self-rating.model";
import {QaErrorHandlerService} from "../../../../portal-core/src/app/_common/services/qa-error-handler.service";

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: './trainee-reflection.component.html',
  styleUrls: ['./trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();
  questionSubscription: Subscription;
  loadingData = true;

  constructor(private ratedQuestionsService: RatedQuestionsService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.selfReflectionViewModel.selfReflectionForm = new SelfReflectionFormModel();
    this.questionSubscription = this.ratedQuestionsService.getSelfReflectionQuestions().subscribe(
      (response) => {
        response.forEach((entry) => {
          const selfRating = new SelfRatingModel();
          selfRating.selfRatingQuestion = entry;
          this.selfReflectionViewModel.selfReflectionForm.selfRatings.push(selfRating);
          this.selfReflectionViewModel.selfReflectionForm.weekCommencing = new Date();
        });
        this.loadingData = false;
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

  ngOnDestroy(): void {
    this.questionSubscription.unsubscribe();
  }
}
