import {Component, OnDestroy, OnInit} from '@angular/core';
import {EvaluationService} from '../_common/services/evaluation-service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {Subscription} from 'rxjs';
import {ICategoryResponse, IFormModel} from 'projects/qa-forms/src/app/_common/models';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';

@Component({
  selector: 'app-trainee-course-evaluation',
  templateUrl: './trainee-course-evaluation.component.html',
  styleUrls: ['./trainee-course-evaluation.component.css']
})
export class TraineeCourseEvaluationComponent implements OnInit, OnDestroy {

  summaryUrl = 'qa/portal/training/feedback/trainee/evaluation/history';

  dataLoaded = false;

  getFormSubscription: Subscription;

  createFormSubscription: Subscription;

  updateFormSubscription: Subscription;

  viewModel: IFormModel;

  constructor(private evaluationService: EvaluationService,
              private errorHandlerService: QaErrorHandlerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    // Get trainee course evaluation for the supplied cohort course id
    this.route.paramMap.subscribe(
      (params: ParamMap) => {
        this.getEvaluationForCohortCourse(params.get('id'));
      }
    );
  }

  ngOnDestroy(): void {
    this.unsubscribe(this.getFormSubscription);
    this.unsubscribe(this.createFormSubscription);
    this.unsubscribe(this.updateFormSubscription);
  }

  onSubmit() {
    this.setFormStatus();
    if (this.isNewForm()) {
      this.createForm();
    } else {
      this.updateForm();
    }
  }

  private createForm(): void {
    this.createFormSubscription = this.evaluationService.createEvaluationForm(this.viewModel)
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          this.navigateToSummary(this.summaryUrl);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private updateForm(): void {
    this.updateFormSubscription = this.evaluationService.updateEvaluationForm(this.viewModel)
      .subscribe((response) => {
          // Navigate to the Evaluation history page for trainee
          this.navigateToSummary(this.summaryUrl);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  private getEvaluationForCohortCourse(cohortCourseId: string) {
    this.getFormSubscription = this.evaluationService.getEvaluationForTraineeAndCohortCourse(cohortCourseId).subscribe(
      (response) => {
        this.viewModel = response;
        this.dataLoaded = true;
      },
      (error) => {
        this.dataLoaded = true;
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

  private navigateToSummary(url: string): void {
    this.router.navigateByUrl(url);
  }

  private unsubscribe(subscription: Subscription) {
    if (!!subscription) {
      subscription.unsubscribe();
    }
  }
}
