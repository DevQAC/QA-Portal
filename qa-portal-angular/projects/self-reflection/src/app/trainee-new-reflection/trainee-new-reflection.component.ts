import {Component, OnDestroy, OnInit} from '@angular/core';
import {SelfReflectionFormViewModel} from '../trainee-reflection/models/self-reflection-form-vmodel';
import {Subscription} from 'rxjs';
import {SelfReflectionFormService} from '../trainee-reflection/services/self-reflection-form.service';
import {QuestionsServiceService} from '../trainee-reflection/services/questions-service.service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {ActivatedRoute, Router} from '@angular/router';
import {SelfReflectionFormModel} from '../_common/models/self-reflection-form-model';
import {ReflectionQuestionModel} from '../_common/models/reflection.question.model';
import {CohortTraineesService} from '../cohort-trainees/services/cohort-trainees.service';
import {SelfReflectionFormStateService} from '../_common/services/self-reflection-form-state.service';
import {QaToastrService} from '../../../../portal-core/src/app/_common/services/qa-toastr.service';
import {SelfReflectionService} from '../trainer-reflection/services/self-reflection.service';
import {QuestionModel} from '../_common/models/question.model';

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

  MILLIS_IN_A_DAY = 24 * 60 * 60 * 1000;

  constructor(private selfReflectionService: SelfReflectionService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService,
              private errorHandlerService: QaErrorHandlerService,
              private selfReflectionFormStateService: SelfReflectionFormStateService,
              private router: Router,
              private route: ActivatedRoute,
              private toastrService: QaToastrService,
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
    let result = new Date(date);
    result = new Date(result.getTime() + days * this.MILLIS_IN_A_DAY);
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
        this.setFormDate(new Date(cohort.startDate));
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

  getSelfReflectionQuestions() {
    this.questionSubscription = this.selfReflectionService.getQuestionsByFormType('reflection_form').subscribe(
      (answers) => {
        answers.forEach((entry) => {
          const reflectionQuestion = new ReflectionQuestionModel();
          this.setReflectionQuestion(reflectionQuestion, entry);
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

  setReflectionQuestion(reflectionQuestion: ReflectionQuestionModel, question: QuestionModel) {
    reflectionQuestion.question = new QuestionModel();
    reflectionQuestion.question.id = question.id;
    reflectionQuestion.question.body = question.body;
    reflectionQuestion.question.questionCategoryName = question.questionCategoryName;
    reflectionQuestion.question.selectionOptionsList = question.selectionOptionsList;
    reflectionQuestion.question.selectionOptionsJson = question.selectionOptionsJson;
  }

  setFormDate(dateToSet: Date) {
    this
      .selfReflectionViewModel
      .selfReflectionForm
      .formDate = dateToSet;
  }

  saveSubmitButtonPress() {
    if (this.isFormCompleted()) {
      this.submitForm();
    } else {
      this.saveForm();
    }
  }

  commitForm() {
    this.selfReflectionFormService.createSelfReflectionForm(this.selfReflectionViewModel.selfReflectionForm)
      .subscribe(
        (response) => {
          this.router.navigateByUrl('qa/portal/training/trainee/selfreflections');
          this.toastrService.showSuccess('Reflection Form ' + this.selfReflectionViewModel.selfReflectionForm.status);
        },
        (error) => {
          this.errorHandlerService.handleError(error);
        }
      );
  }

  submitForm() {
    this.selfReflectionViewModel.selfReflectionForm.status = 'Submitted';
    this.commitForm();
  }

  saveForm() {
    this.selfReflectionViewModel.selfReflectionForm.status = 'Saved';
    this.commitForm();
  }

  isFormCompleted(): boolean {
    return this.selfReflectionFormStateService.isFormCompleted(this.selfReflectionViewModel.selfReflectionForm);
  }

  disable(): boolean {
    return this.selfReflectionFormStateService.disable(this.selfReflectionViewModel.selfReflectionForm);
  }

  ngOnDestroy(): void {
    if (!!this.questionSubscription) {
      this.questionSubscription.unsubscribe();
    }
  }
}
