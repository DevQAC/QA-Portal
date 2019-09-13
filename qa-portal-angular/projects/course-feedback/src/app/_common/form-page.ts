import {Subscription} from 'rxjs';
import {ICategoryResponse, IFormModel} from '../../../../qa-forms/src/app/_common/models';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {IFormService} from './services/iform.service';

export abstract class FormPage {

  public dataLoaded = false;

  protected getFormSubscription: Subscription;

  protected createFormSubscription: Subscription;

  protected updateFormSubscription: Subscription;

  protected viewModel: IFormModel;

  constructor(protected formService: IFormService,
              protected errorHandlerService: QaErrorHandlerService,
              protected router: Router,
              protected route: ActivatedRoute,
              protected summaryUrl: string) {
  }

  protected initialiseForm() {
    this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.getForm(params.get('id'));
      });
  }

  protected clearSubscriptions() {
    this.unsubscribe(this.getFormSubscription);
    this.unsubscribe(this.createFormSubscription);
    this.unsubscribe(this.updateFormSubscription);
  }

  protected navigateToSummary(url: string): void {
    this.router.navigateByUrl(url);
  }

  protected onSubmit() {
    this.setFormStatus();
    if (this.isNewForm()) {
      this.createForm();
    } else {
      this.updateForm();
    }
  }

  private getForm(id: string) {
    this.getFormSubscription = this.formService.getForm(id)
      .subscribe(
        (response) => {
          this.viewModel = response;
          this.dataLoaded = true;
        },
        (error) => {
          this.dataLoaded = true;
          this.errorHandlerService.handleError(error);
        });
  }

  private createForm(): void {
    this.createFormSubscription = this.formService.createForm(this.viewModel)
      .subscribe((response) => {
          this.navigateToSummary(this.summaryUrl);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private updateForm(): void {
    this.updateFormSubscription = this.formService.updateForm(this.viewModel)
      .subscribe((response) => {
          this.navigateToSummary(this.summaryUrl);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private setFormStatus(): void {
    this.viewModel.status = this.formCompleted() ? 'Submitted' : 'Saved';
  }

  private isNewForm(): boolean {
    return !this.viewModel.id;
  }

  private formCompleted(): boolean {
    const incompleteQuestionCategory = this.viewModel.categoryResponses
      .find(cr => {
        return !this.questionsAnswered(cr);
      });
    return !incompleteQuestionCategory;
  }

  private questionsAnswered(categoryResponse: ICategoryResponse): boolean {
    const questionResponse = categoryResponse.questionResponses.find(qr => {
      return !qr.responseValues || qr.responseValues.length === 0;
    });
    return !questionResponse;
  }

  private unsubscribe(subscription: Subscription) {
    if (!!subscription) {
      subscription.unsubscribe();
    }
  }
}

