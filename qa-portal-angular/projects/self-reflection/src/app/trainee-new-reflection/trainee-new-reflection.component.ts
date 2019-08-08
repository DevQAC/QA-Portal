import {Component, OnDestroy, OnInit} from '@angular/core';
import {SelfReflectionFormViewModel} from '../trainee-reflection/models/self-reflection-form-vmodel';
import {Subscription} from 'rxjs';
import {RatedQuestionsService} from '../trainee-reflection/services/rated-questions.service';
import {SelfReflectionFormService} from '../trainee-reflection/services/self-reflection-form.service';
import {QuestionsServiceService} from '../trainee-reflection/services/questions-service.service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SelfReflectionFormModel} from '../_common/models/self-reflection-form-model';
import {ReflectionQuestionModel} from '../_common/models/reflection.question.model';

@Component({
  selector: 'app-trainee-new-reflection',
  templateUrl: '../_common/templates/trainee-reflection.component.html',
  styleUrls: ['../_common/css/trainee-reflection.component.css'] 
})
export class TraineeNewReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();

  questionSubscription: Subscription;

  loadingData = true;

  constructor(private ratedQuestionsService: RatedQuestionsService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService,
              private router: Router,
              private route: ActivatedRoute) {
  }

  ngOnInit() {
    this.intialiseSelfReflectionForm();
  }

  ngOnDestroy(): void {
    this.questionSubscription.unsubscribe();
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
          const reflectionQuestion = new ReflectionQuestionModel();
          reflectionQuestion.question = entry;
          this.selfReflectionViewModel.selfReflectionForm.reflectionQuestions.push(reflectionQuestion);
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
