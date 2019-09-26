import {Component, OnInit} from '@angular/core';
import {ReflectionModel} from './models/dto/reflection.model';
import {ActivatedRoute, ParamMap, Router} from '@angular/router';
import {QaToastrService} from '../../../../portal-core/src/app/_common/services/qa-toastr.service';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {TraineeModel} from './models/dto/trainee.model';
import {ScottTrainerReflectionService} from './services/scott-trainer-reflection.service';
import {ReflectionQuestionModel} from './models/dto/reflection-question.model';

@Component({
  selector: 'app-scott-trainer-reflection',
  templateUrl: './scott-trainer-reflection.component.html',
  styleUrls: ['./trainer-reflection.component.css']
})
export class ScottTrainerReflectionComponent implements OnInit {

  viewModel: ReflectionModel[];

  currentReflection: ReflectionModel;

  previousReflections: ReflectionModel[];

  trainee: TraineeModel;

  categoryReflectionQuestions: any;

  categoryColumnHeaders: any;

  loadingData = true;

  constructor(
    private reflectionService: ScottTrainerReflectionService,
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
    this.reflectionService.saveSelfReflectionForm(this.currentReflection).subscribe((reflection) => {
        this.router.navigateByUrl('qa/portal/training/self-reflection/trainer/cohorts/trainees');
      },
      (error) => {
        this.errorService.handleError(error);
      });
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

  getColumnId(columnId: number): string {
    return '' + columnId;
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
    this.categoryReflectionQuestions = {};
    this.categoryColumnHeaders = {};
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
    this.currentReflection = this.viewModel[0];
  }

  private setPreviousReflections(): ReflectionModel[] {
    return this.previousReflections = this.viewModel.slice(1);
  }

  private questionsCompleted(): boolean {
    const questionsCompleted = true;
    return questionsCompleted;
  }
}
