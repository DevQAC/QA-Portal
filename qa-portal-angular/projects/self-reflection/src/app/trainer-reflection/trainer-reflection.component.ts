import { Component, OnInit } from '@angular/core';
import { Tile } from './models/tile';
import { SelfReflectionService } from './services/self-reflection.service';
import { Reflection } from './models/dto/reflection';
import { Trainee } from './models/dto/trainee';
import { Question } from './models/dto/question';
import { ReflectionQuestion } from './models/dto/reflection-question';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { ScoreTile } from './models/score-tile';
import { UserType } from './models/user-type.enum';
import { MatSnackBar } from '@angular/material';
import { CohortQuestion } from './models/dto/cohort-question';

@Component({
  selector: 'app-trainer-reflection',
  templateUrl: './trainer-reflection.component.html',
  styleUrls: ['./trainer-reflection.component.css']
})
export class TrainerReflectionComponent implements OnInit {

  public questionRow: Tile[] = [];
  public authorRow: Tile[] = [];
  public scoreRow: ScoreTile[] = [];
  public COL_MAX = 24;
  public trainee: Trainee = new Trainee();
  public reflections: Reflection[] = [];
  public questions: Question[] = [];
  public trainerComments = '';
  public learningPathway = '';
  public numberOfCategories = 0;
  public categories = [];
  public disableInputs = false;
  private traineeId = 0;
  public statusMessage = 'Checking for Self Reflections...';

  constructor(private selfReflectionService: SelfReflectionService, private activatedRoute: ActivatedRoute, private snackBar: MatSnackBar) {
    this.trainerComments = this.learningPathway = 'Ea qui ipsum sint nisi et sunt et eu commodo proident id.' +
      'Exercitation adipisicing ut aute consequat pariatur minim duis cupidatat velit quis. Qui ' +
      'consectetur reprehenderit nisi deserunt adipisicing velit enim quis cillum eiusmod. Minim ea mollit in ' +
      'eu tempor tempor quis.';
  }

  private updateReflections() {
    this.scoreRow = [];
    for (const reflection of this.reflections) {
      for (const reflectionQuestion of reflection.reflectionQuestions) {
        const traineeResponse = reflectionQuestion.response ? reflectionQuestion.response.toString() : null;
        const trainerResponse = reflectionQuestion.trainerResponse ? reflectionQuestion.trainerResponse.toString() : null;
        this.scoreRow.push({ colspan: 2, text: traineeResponse, data: reflectionQuestion, userType: UserType.TRAINEE });
        this.scoreRow.push({ colspan: 2, text: trainerResponse, data: reflectionQuestion, userType: UserType.TRAINER });
      }
    }
  }

  public saveReflectionQuestions(): void {
    console.log('Saving ReflectionQuestions!');
    this.disableInputs = true;
    const reflectionQuestions: ReflectionQuestion[] = [];
    this.scoreRow.forEach((scoreTile: ScoreTile): void => {
      const score = scoreTile.text !== null ? Math.max(Math.min(+scoreTile.text, 10), 1) : null;
      switch (scoreTile.userType) {
        case UserType.TRAINEE:
          scoreTile.data.response = score;
          break;
        case UserType.TRAINER:
          scoreTile.data.trainerResponse = score;
          break;
        default:
      }
      if (scoreTile.data.id !== null) {
        if (!reflectionQuestions.find(rq => rq === scoreTile.data)) {
          reflectionQuestions.push(scoreTile.data);
        }
      }
      if (scoreTile.text) {
        scoreTile.text = score.toString();
      }
    });
    // Send all the reflection questions to the backend
    // On successful save, re-enable input fields and show snackbar.
    this.selfReflectionService.updateReflectionQuestions(reflectionQuestions)
      .subscribe(updatedReflections => {
        this.snackBar.open('Self Reflections Updated.', 'Dismiss', { duration: 3000 });
        this.disableInputs = false;
      }, error => {
        // TODO: Use general error service
        this.snackBar.open('Error: Self Reflection not saved (see console).', 'Dismiss', { duration: 3000 });
        console.log(error);
      });
  }

  public saveTrainerComments(): void {
    console.log('Saving TrainerComments!');
  }

  public saveLearningPathway(): void {
    console.log('Saving Learning Pathway!');
  }

  private handleError(error): void {
    console.log(error);
    this.statusMessage = 'Error: Self Reflections could not be loaded.';
  }

  ngOnInit() {
    // Get trainee id from path
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap): void => {
      this.traineeId = +paramMap.get('id');
    });
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
          if (a.category < b.category) {
            return -1;
          } else if (a.category > b.category) {
            return 1;
          } else {
            return 0;
          }
        });
        this.questions.forEach(question => {
          if (!this.categories.includes(question.category)) {
            this.categories.push(question.category);
          }
        });
        console.log((this.categories.length / this.COL_MAX * 100));
        // Create Self/Trainer tiles for view
        for (let i = 0; i < this.numberOfCategories; ++i) {
          this.authorRow.push({ colspan: 2, text: 'Self' });
          this.authorRow.push({ colspan: 2, text: 'Trainer' });
        }
        // Get reflections for this user
        this.selfReflectionService.getReflectionsByTraineeId(this.traineeId)
          .subscribe(reflections => {
            if (reflections && reflections.length > 0) {
              this.trainee = reflections[0].responder;
              reflections.forEach((reflection: Reflection): void => {
                this.selfReflectionService.getReflectionQuestionsByReflectionId(reflection.id)
                  .subscribe((reflectionQuestions: ReflectionQuestion[]): void => {
                    Reflection.setReflectionQuestions(reflection, reflectionQuestions, this.categories.length);
                    this.reflections.push(reflection);
                    this.updateReflections();
                  });
              });
            } else {
              this.statusMessage = 'There are no Self Reflections for this user.';
            }
          },
            error => this.handleError(error));
      },
        error => this.handleError(error));
  }
}
