import {Component, OnDestroy, OnInit} from '@angular/core';
import {QuestionsServiceService} from './services/questions-service.service';
import {SelfReflectionFormViewModel} from './models/self-reflection-form-vmodel';
import {RatedQuestionsService} from './services/rated-questions.service';
import {SelfReflectionFormService} from './services/self-reflection-form.service';
import {Subscription} from 'rxjs';
import {SelfReflectionFormModel} from '../_common/models/self-reflection-form-model';
import {ReflectionQuestionModel} from '../_common/models/reflection.question.model';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {Router} from '@angular/router';
import {subscriptionLogsToBeFn} from 'rxjs/internal/testing/TestScheduler';

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: './trainee-reflection.component.html',
  styleUrls: ['./trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();

  currentReflectionSubscription: Subscription;

  questionSubscription: Subscription;

  loadingData = true;

  constructor(private ratedQuestionsService: RatedQuestionsService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService,
              private router: Router) {
  }

  ngOnInit() {
    this.intialiseSelfReflectionForm();

    // this.currentReflectionSubscription = this.selfReflectionFormService.getCurrentSelfReflectionForm().subscribe(
    //   (response) => {
    //     if (!!response.id) {
    //       this.selfReflectionViewModel.selfReflectionForm = response;
    //     } else {
    //       this.intialiseSelfReflectionForm();
    //     }
    //   },
    //   (error) => {
    //     this.errorHandlerService.handleError(error);
    //   });
  }

  ngOnDestroy(): void {
    this.questionSubscription.unsubscribe();
    // this.currentReflectionSubscription.unsubscribe();
  }

  submitForm() {
    this.selfReflectionFormService.createSelfReflectionForm(this.selfReflectionViewModel.selfReflectionForm).subscribe(
      (response) => {
        this.router.navigateByUrl('qa/portal/training/trainee/selfreflections');
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

  private intialiseSelfReflectionForm(): void {
    this.selfReflectionViewModel.selfReflectionForm = new SelfReflectionFormModel();
    this.questionSubscription = this.ratedQuestionsService.getSelfReflectionQuestions().subscribe(
      (response) => {
        response.forEach((entry) => {
          const selfRating = new ReflectionQuestionModel();
          selfRating.question = entry;
          this.selfReflectionViewModel.selfReflectionForm.reflectionQuestions.push(selfRating);
          this.selfReflectionViewModel.selfReflectionForm.formDate = new Date();
        });
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );
  }
}
