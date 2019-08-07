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
import {CohortTraineesService} from '../cohort-trainees/services/cohort-trainees.service';
import {element} from 'protractor';

@Component({
  selector: 'app-trainee-new-reflection',
  templateUrl: '../_common/templates/trainee-reflection.component.html',
  styleUrls: ['../_common/css/trainee-reflection.component.css']
})
export class TraineeNewReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();

  questionSubscription: Subscription;

  loadingData = true;

  listOfFormDates;

  buttonLabel;


  constructor(private ratedQuestionsService: RatedQuestionsService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService,
              private router: Router,
              private route: ActivatedRoute,
              private cohortTraineesService: CohortTraineesService) {
  }

  ngOnInit() {
    this.initialiseForm();
  }

  getSortedDateArray(arrayOfDates: []): Date[] {
    return arrayOfDates.sort(
      (a, b) => {
        const dateA = new Date(a);
        const dateB = new Date(b);
        if (dateA > dateB) {
          return 1;
        } else if (dateA < dateB) {
          return -1;
        } else {
          return 0;
        }
      }
    );
  }

  addDays(date: Date, days: number) {
    const result = new Date(date);
    result.setDate(result.getDate() + days);
    return result;
  }

  initialiseForm() {
    this.selfReflectionFormService.getAllReflectionFormsForUser().subscribe(
      (response) => {
        this.selfReflectionViewModel.selfReflectionForm = new SelfReflectionFormModel();
        this.listOfFormDates = [];
        response.forEach((element) => this.checkIfReviewed(element));

        if (this.listOfFormDates.length === 0) {
          this.setInitialFormDateFromCohortStartDate();
        } else {
          this.setFormDateFromExisting();
        }
        this.getSelfReflectionQuestions();

      }
    );
  }

  checkIfReviewed(element) {
    if (element.status === 'Reviewed') {
      this.listOfFormDates.push(element.formDate);
    } else {
      this.router.navigateByUrl('qa/portal/training/trainee/selfreflection/' + element.id);
    }
  }

  setFormDateFromExisting() {
    const sortedList = this.getSortedDateArray(this.listOfFormDates);
    const testDate = sortedList[sortedList.length - 1];

    const date = new Date(testDate);
    this.setFormDate(this.addDays(date, 7));

  }

  setInitialFormDateFromCohortStartDate() {
    this.cohortTraineesService.getCohort().subscribe(
      (cohort) => {
        console.log(cohort.startDate);
        this.setFormDate(new Date(cohort.startDate));
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

  getSelfReflectionQuestions() {
    this.questionSubscription = this.ratedQuestionsService.getSelfReflectionQuestions().subscribe(
      (answer) => {
        answer.forEach((entry) => {
          const reflectionQuestion = new ReflectionQuestionModel();
          reflectionQuestion.question = entry;
          this.selfReflectionViewModel.selfReflectionForm.reflectionQuestions.push(reflectionQuestion);
        });
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );

  }

  setFormDate(dateToSet: Date) {
    this
      .selfReflectionViewModel
      .selfReflectionForm
      .formDate = dateToSet;
  }

  submitForm() {
    this.selfReflectionViewModel.selfReflectionForm.status = 'Submitted';
    this.commitForm();
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

  commitForm() {
    this.selfReflectionFormService.createSelfReflectionForm(this.selfReflectionViewModel.selfReflectionForm)
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

  saveSubmitButtonPress() {
    if (this.getButtonLabel() === 'Submit') {
      this.submitForm();
    } else {
      this.saveForm();
    }

  }

  saveForm() {
    this.selfReflectionViewModel.selfReflectionForm.status = 'Saved';
    this.commitForm();
  }

  ngOnDestroy(): void {
    if (!!this.questionSubscription) {
      this.questionSubscription.unsubscribe();
    }
  }
}
