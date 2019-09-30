import {Component, OnDestroy, OnInit} from '@angular/core';
import {QuestionsServiceService} from './services/questions-service.service';
import {SelfReflectionFormViewModel} from './models/self-reflection-form-vmodel';
import {SelfReflectionFormService} from './services/self-reflection-form.service';
import {Subscription} from 'rxjs';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {SelfReflectionFormStateService} from '../_common/services/self-reflection-form-state.service';
import {QaToastrService} from '../../../../portal-core/src/app/_common/services/qa-toastr.service';

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: '../_common/templates/trainee-reflection.component.html',
  styleUrls: ['../_common/css/trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();

  currentReflectionSubscription: Subscription;

  loadingData = true;

  constructor(private selfReflectionFormService: SelfReflectionFormService,
              private selfReflectionFormStateService: SelfReflectionFormStateService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService,
              private toastrService: QaToastrService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    console.log('In TraineeReflectionComponent');
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


  saveSubmitButtonPress() {
    if (this.isFormCompleted()) {
      this.submitForm();
    } else {
      this.selfReflectionViewModel.selfReflectionForm.status = 'Saved';
      this.updateForm();
    }
  }

  updateForm() {
    this.selfReflectionFormService.updateSelfReflectionForm(this.selfReflectionViewModel.selfReflectionForm)
      .subscribe(
        (response) => {
          this.router.navigateByUrl('qa/portal/training/self-reflection/trainee/history');
          this.toastrService.showSuccess('Reflection Form ' + this.selfReflectionViewModel.selfReflectionForm.status);
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

  isFormCompleted() {
    return this.selfReflectionFormStateService.isFormCompleted(this.selfReflectionViewModel.selfReflectionForm);
  }

  disable(): boolean {
    return this.selfReflectionFormStateService.disable(this.selfReflectionViewModel.selfReflectionForm);
  }

  getArrayFromOptionsString(optionsString: string): string[] {
    return JSON.parse(optionsString);
  }
}
