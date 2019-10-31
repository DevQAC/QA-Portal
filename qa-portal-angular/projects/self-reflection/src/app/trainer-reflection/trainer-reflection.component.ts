import {Component, OnInit} from '@angular/core';
import {ReflectionModel} from './models/dto/reflection.model';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {QaToastrService} from '../../../../portal-core/src/app/_common/services/qa-toastr.service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {TraineeModel} from './models/dto/trainee.model';
import {TrainerReflectionService} from './services/trainer-reflection.service';
import {ReflectionQuestionModel} from './models/dto/reflection-question.model';

@Component({
  selector: 'app-scott-trainer-reflection',
  templateUrl: './trainer-reflection.component.html',
  styleUrls: ['./trainer-reflection.component.css']
})
export class TrainerReflectionComponent implements OnInit {

  viewModel: ReflectionModel[];

  currentReflection: ReflectionModel;

  previousReflections: ReflectionModel[];

  trainee: TraineeModel;

  categoryReflectionQuestions: any;

  categoryColumnHeaders: any;

  trainerRatingColumn = ['trainer-rating'];

  loadingData = true;

  constructor(
    private reflectionService: TrainerReflectionService,
    private activatedRoute: ActivatedRoute,
    private toastrService: QaToastrService,
    private errorService: QaErrorHandlerService,
    private router: Router) {
  }

  ngOnInit(): void {
    this.activatedRoute.paramMap.subscribe((pm: ParamMap): void => {
      const traineeId = +pm.get('id');
      this.initialiseView(traineeId);
    });
  }

  onSubmit() {
    if (this.isFormCompleted()) {
      this.currentReflection.status = 'Reviewed';
    }

    const invalidTrainerResponse = this.invalidTrainerResponse();

    if (!!invalidTrainerResponse) {
      this.errorService.showError('Invalid response entered for category ' + invalidTrainerResponse.question.questionCategoryName);
    } else {
      this.reflectionService.saveSelfReflectionForm(this.currentReflection).subscribe((reflection) => {
          this.router.navigateByUrl('qa/portal/training/self-reflection/trainer/cohorts/trainees');
        },
        (error) => {
          this.errorService.handleError(error);
        });
    }
  }

  isFormCompleted() {
    return !!this.currentReflection.learningPathway &&
      !!this.currentReflection.trainerFeedback &&
      this.questionsCompleted();
  }

  categoryNames(): string[] {
    return Object.keys(this.categoryReflectionQuestions);
  }

  getQuestionResponseValueForReflection(reflection: ReflectionModel, questionId: number): number {
    const reflectionQuestion = reflection.reflectionQuestions.find(rq => rq.question.id === questionId);
    return reflectionQuestion.response;
  }

  getReflectionColumnHeaders(categoryName: string): string[] {
    return ['start-date', ...this.categoryColumnHeaders[categoryName]];
  }

  getColumnIdAsString(columnId: number): string {
    return '' + columnId;
  }

  getTrainerResponse(reflection: ReflectionModel, categoryName: string): any {
    const reflectionQuestion = reflection.reflectionQuestions.find(rq => {
      return !!rq && rq.question.questionCategoryName === categoryName && !!rq.trainerResponse;
    });
    return !!reflectionQuestion ? reflectionQuestion.trainerResponse : '';
  }

  private initialiseView(traineeId: number) {
    this.getSelfReflectionsForTrainee(traineeId);
  }

  private getSelfReflectionsForTrainee(traineeId: number) {
    this.reflectionService.getSelfReflectionsForTraineeDescendingDate(traineeId).subscribe(
      (reflections) => {
        if (this.noReflectionsForTrainee(reflections)) {
          this.loadingData = false;
          this.errorService.showError('No reflections for trainee');
        } else {
          this.populateView(reflections);
          this.loadingData = false;
        }
      },
      (error) => {
        this.loadingData = false;
        this.errorService.handleError(error);
      });
  }

  private noReflectionsForTrainee(reflections: ReflectionModel[]): boolean {
    return !reflections || reflections.length === 0;
  }

  private populateView(reflections: ReflectionModel[]) {
    this.viewModel = reflections;
    this.setCurrentReflection();
    this.setPreviousReflections();
    this.setCategoryQuestionsForCurrentReflection();
    this.setTrainee();
  }

  private setCategoryQuestionsForCurrentReflection() {
    this.categoryReflectionQuestions = {};   // this is a map of <string, ReflectionQuestionModel[]), where key is the category name
    this.categoryColumnHeaders = {};         // this is a map of <string, string[]), where key is category name and value is array of
                                             // question ids.
    this.currentReflection.reflectionQuestions.forEach((reflectionQuestion) => {
      if (!Object.keys(this.categoryReflectionQuestions).includes(reflectionQuestion.question.questionCategoryName)) {
        this.categoryReflectionQuestions[reflectionQuestion.question.questionCategoryName] = [];
        this.categoryColumnHeaders[reflectionQuestion.question.questionCategoryName] = [];
      }
      this.categoryReflectionQuestions[reflectionQuestion.question.questionCategoryName].push(reflectionQuestion);
      this.categoryColumnHeaders[reflectionQuestion.question.questionCategoryName].push('' + reflectionQuestion.question.id);
    });
  }

  private setTrainee() {
    this.trainee = this.viewModel[0].responder;
  }

  private setCurrentReflection() {
    // Get the current reflection (first one as ordered by descending form date)
    this.currentReflection = this.viewModel[0];
  }

  private setPreviousReflections(): ReflectionModel[] {
    // Remove current reflection from the reflection array
    return this.previousReflections = this.viewModel.slice(1);
  }

  private questionsCompleted(): boolean {
    // Check that all categories have a reflection question with a trainer response value
    return !this.categoryNames().some(category => {
      return !this.categoryReflectionQuestions[category][0].trainerResponse;
    });
  }

  private invalidTrainerResponse(): ReflectionQuestionModel {
    // Find a category that has a Reflection Question with an invalid response or else return null
    const invalidCategory = this.categoryNames().find(category => {
        return !this.validResponse(this.categoryReflectionQuestions[category][0].trainerResponse);
      }
    );
    return !!invalidCategory ? this.categoryReflectionQuestions[invalidCategory][0] : null;
  }

  private validResponse(trainerResponse: any) {
    // If not response then it's valid - reflection cannot be submitted but can be saved
    if (!trainerResponse) {
      return true;
    }

    // If not a number then invalid
    if (isNaN(trainerResponse)) {
      return false;
    }

    // If Integer between 1 and 10 (inclusive) then valid
    if (Number.isInteger(+trainerResponse) && +trainerResponse >= 1 && +trainerResponse <= 10) {
      return true;
    }

    // Otherwise invalid.
    return false;
  }
}
