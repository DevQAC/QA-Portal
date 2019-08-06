import {Component, OnDestroy, OnInit} from '@angular/core';
import {QuestionsServiceService} from './services/questions-service.service';
import {SelfReflectionFormViewModel} from './models/self-reflection-form-vmodel';
import {RatedQuestionsService} from './services/rated-questions.service';
import {SelfReflectionFormService} from './services/self-reflection-form.service';
import {Subscription} from 'rxjs';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: '../_common/templates/trainee-reflection.component.html',
  styleUrls: ['../_common/css/trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();

  currentReflectionSubscription: Subscription;

  loadingData = true;

  constructor(private ratedQuestionsService: RatedQuestionsService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.populateSelfReflectionForm(params.get('id'));
      }
    );
  }

  ngOnDestroy(): void {
    this.currentReflectionSubscription.unsubscribe();
  }

  private populateSelfReflectionForm(formId: string): void {
    this.currentReflectionSubscription = this.selfReflectionFormService.getSelfReflectionForm(formId).subscribe(
      (response) => {
        if (!!response.id) {
          this.selfReflectionViewModel.selfReflectionForm = response;
          this.loadingData = false;
        } else {
          this.errorHandlerService.showError('No Form found for id ' + formId);
        }
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      });
  }
  
  updateForm() {
    this.selfReflectionFormService.updateSelfReflectionForm(this.selfReflectionViewModel.selfReflectionForm)
      .subscribe(
        (response) => {
          this.router.navigateByUrl('qa/portal/training/trainee/selfreflections');
          console.log(this.selfReflectionViewModel);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  submitForm() {
    this.selfReflectionViewModel.selfReflectionForm.status = 'Submitted';
    this.updateForm();
  }

  getButtonLabel() {
    let label = 'Submit';
    if (!(!!this.selfReflectionViewModel.selfReflectionForm.strengths
      && !!this.selfReflectionViewModel.selfReflectionForm.weaknesses
      && !!this.selfReflectionViewModel.selfReflectionForm.opportunities
      && !!this.selfReflectionViewModel.selfReflectionForm.threats)) {

      label = 'Save';
    } else {
      this.selfReflectionViewModel.selfReflectionForm.reflectionQuestions.forEach((question) => {
        if (!question.response) {
          label = 'Save';
        }
      });
    }
    return label;
  }

  saveSubmitButtonPress() {
    if (this.getButtonLabel() === 'Submit') {
      this.submitForm();
    } else {
      this.updateForm();
    }
  }
}
