import { Component, OnInit } from '@angular/core';
import { SelfReflectionService } from './services/self-reflection.service';
import { Reflection } from './models/dto/reflection';
import { Trainee } from './models/dto/trainee';
import { Question } from './models/dto/question';
import { ReflectionQuestion } from './models/dto/reflection-question';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { MatSnackBar } from '@angular/material';
import { RowData } from './models/row-data';
import { QaToastrService } from '.././../../../portal-core/src/app/_common/services/qa-toastr.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-trainer-reflection',
  templateUrl: './trainer-reflection.component.html',
  styleUrls: ['./trainer-reflection.component.css']
})
export class TrainerReflectionComponent implements OnInit {

  public COL_MAX = 24;
  public trainee: Trainee = new Trainee();
  public reflections: Reflection[] = [];
  public questions: Question[] = [];
  public trainerComments = '';
  public learningPathway = '';
  public rowData: RowData[] = [];
  public disableInputs = false;
  public questionIds = [];
  public loaded = false;
  public statusMessage = 'Checking for Self Reflections...';
  public authors = ['Self', 'Trainer'];

  constructor(
    private selfReflectionService: SelfReflectionService, private activatedRoute: ActivatedRoute, private snackBar: MatSnackBar,
    private toastrService: QaToastrService, private errorService: QaErrorHandlerService) {
    this.trainerComments = this.learningPathway = 'Ea qui ipsum sint nisi et sunt et eu commodo proident id.' +
      'Exercitation adipisicing ut aute consequat pariatur minim duis cupidatat velit quis. Qui ' +
      'consectetur reprehenderit nisi deserunt adipisicing velit enim quis cillum eiusmod. Minim ea mollit in ' +
      'eu tempor tempor quis.';
  }

  private updateReflections() {
    this.loaded = true;
    this.reflections.sort((a, b): number => {
      const aVal = new Date(a.lastUpdatedTimestamp);
      const bVal = new Date(b.lastUpdatedTimestamp);
      if (aVal > bVal) {
        return -1;
      } else if (aVal < bVal) {
        return 1;
      } else {
        return 0;
      }
    });
    for (const reflection of this.reflections) {
      for (const reflectionQuestion of reflection.reflectionQuestions) {
        this.rowData.forEach((rowData) => {
          const q = rowData.questions.find(question => question.id === reflectionQuestion.question.id);
          if (q) {
            q.reflectionQuestions.push(reflectionQuestion);
          }
        });
      }
    }
  }

  public saveReflectionQuestions(): void {
    console.log('Saving ReflectionQuestions!');
    this.disableInputs = true;
    const reflectionQuestions: ReflectionQuestion[] = [];
    const betweenOneAndTen = (i) => Math.min(Math.max(1, i), 10);
    for (const reflection of this.reflections) {
      for (const reflectionQuestion of reflection.reflectionQuestions) {
        if (reflectionQuestion.response) {
          reflectionQuestion.response = betweenOneAndTen(reflectionQuestion.response);
        }
        if (reflectionQuestion.trainerResponse) {
          reflectionQuestion.trainerResponse = betweenOneAndTen(reflectionQuestion.trainerResponse);
        }
        if (reflectionQuestion.id !== null) {
          reflectionQuestions.push(reflectionQuestion);
        }
      }
    }
    // Send all the reflection questions to the backend
    // On successful save, re-enable input fields and show snackbar.
    this.selfReflectionService.updateReflectionQuestions(reflectionQuestions)
      .subscribe(updatedReflections => {
        // this.snackBar.open('Self Reflections Updated.', 'Dismiss', { duration: 3000 });
        this.toastrService.showSuccess('Reflection successfully updated.');
        this.disableInputs = false;
      }, error => {
        // TODO: Use general error service
        this.errorService.handleError(error);
      });
  }

  public saveTrainerComments(): void {
    console.log('Saving TrainerComments!');
  }

  public saveLearningPathway(): void {
    console.log('Saving Learning Pathway!');
  }

  ngOnInit() {
    // Get trainee id from path
    let traineeId;
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap): void => {
      traineeId = +paramMap.get('id');
    }, error => this.errorService.handleError(error));
    // Get questions.
    // TODO: remove hard-coded value
    this.trainee = {
      id: 9,
      userName: 'smith01',
      firstName: 'John',
      lastName: 'Smith',
      cohortId: 1,
      role: 'TRAINEE',
      reviewerId: 8
    };

    this.selfReflectionService.getQuestionsByCohortId(this.trainee.cohortId)
      .subscribe(questions => {
        this.questions = questions.sort((a, b) => {
          const aVal = a.id;
          const bVal = b.id;
          if (aVal < bVal) {
            return -1;
          } else if (aVal > bVal) {
            return 1;
          } else {
            return 0;
          }
        });
        this.questions.forEach(question => {
          const categories = this.rowData.map(rowData => rowData.category);
          if (!categories.includes(question.category)) {
            this.rowData.push(
              {
                category: question.category,
                questions: [],
              }
            );
          }
          if (!this.questionIds.includes(question.id)) {
            this.questionIds.push(question.id);
          }
        });
        for (const question of this.questions) {
          const category = this.rowData.find(rowData => rowData.category === question.category);
          if (category !== undefined) {
            category.questions.push({ id: question.id, body: question.body, reflectionQuestions: [] });
          }
        }
        // Get reflections for this user
        this.selfReflectionService.getReflectionsByTraineeId(traineeId)
          .subscribe(reflections => {
            if (reflections && reflections.length > 0) {
              let num = 0;
              // TODO: Change to async
              reflections.forEach((reflection: Reflection, index: number): void => {
                this.selfReflectionService.getReflectionQuestionsByReflectionId(reflection.id)
                  .subscribe((reflectionQuestions: ReflectionQuestion[]): void => {
                    Reflection.setReflectionQuestions(reflection, reflectionQuestions, this.questionIds);
                    this.reflections.push(reflection);
                    if (num === reflections.length - 1) {
                      this.updateReflections();
                    } else {
                      ++num;
                    }
                  });
              }, error => this.errorService.handleError(error));
            } else {
              this.statusMessage = 'There are no Self Reflections for this user.';
            }
          }, error => this.errorService.handleError(error));
      }, error => this.errorService.handleError(error));
  }
}
