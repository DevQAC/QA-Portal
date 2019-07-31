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

  submitForm() {

    this.currentReflectionSubscription =
                      this.selfReflectionFormService.updateSelfReflectionForm(this.selfReflectionViewModel.selfReflectionForm)
      .subscribe(
      (response) => {
        this.router.navigateByUrl('qa/portal/training/trainee/selfreflections');
        console.log(this.selfReflectionViewModel)
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
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
}
