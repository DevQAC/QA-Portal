import { Component, OnInit } from '@angular/core';
import { Tile } from './models/tileRow';
import { SelfReflectionService } from './services/self-reflection.service';
import { Reflection } from './models/dto/reflection';
import { Trainee } from './models/dto/trainee';
import { Question } from './models/dto/question';
import { ReflectionQuestion } from './models/dto/reflection-question';
import { ActivatedRoute, ParamMap } from '@angular/router';

@Component({
  selector: 'app-trainer-reflection',
  templateUrl: './trainer-reflection.component.html',
  styleUrls: ['./trainer-reflection.component.css']
})
export class TrainerReflectionComponent implements OnInit {

  public titleRow: Tile[] = [];
  public questionRow: Tile[] = [];
  public authorRow: Tile[] = [];
  public scoreRow: Tile[] = [];
  public COL_MAX = 0;
  public trainee: Trainee = new Trainee();
  public reflections: Reflection[] = [];
  public questions: Question[] = [];
  public trainerComments = '';
  public learningPathway = '';
  public numberOfCategories = 0;
  public skillAreas = ['Technical Skills', 'Soft Skills', 'Attitude'];
  private traineeId = 0;

  constructor(private selfReflectionService: SelfReflectionService, private activatedRoute: ActivatedRoute) {
    this.trainerComments = this.learningPathway = 'Ea qui ipsum sint nisi et sunt et eu commodo proident id.' +
      'Exercitation adipisicing ut aute consequat pariatur minim duis cupidatat velit quis. Qui ' +
      'consectetur reprehenderit nisi deserunt adipisicing velit enim quis cillum eiusmod. Minim ea mollit in ' +
      'eu tempor tempor quis.';
  }

  private updateReflections() {
    for (const reflection of this.reflections) {
      for (const reflectionQuestion of reflection.reflectionQuestions) {
        const traineeResponse = reflectionQuestion.response ? reflectionQuestion.response.toString() : 'N/A';
        const trainerResponse = reflectionQuestion.trainerResponse ? reflectionQuestion.trainerResponse.toString() : 'N/A';
        this.scoreRow.push({ colspan: 2, text: traineeResponse });
        this.scoreRow.push({ colspan: 2, text: trainerResponse });
      }
    }
  }

  public floor(arg) {
    return Math.floor(arg);
  }

  ngOnInit() {
    // const fakeReflection: Reflection = {
    //   responder: { firstName: 'Gordon', lastName: 'Wells', userName: 'user' },
    //   reviewer: { firstName: 'Alan', lastName: 'Alanadopoulous', userName: 'aaaalan' },
    //   formDate: new Date(),
    //   reflectionQuestions: []
    // };
    // Trainee id
    this.activatedRoute.paramMap.subscribe((paramMap: ParamMap): void => {
      this.traineeId = +paramMap.get('id');
    });
    // Get questions
    this.selfReflectionService.getQuestions(1)
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
        this.numberOfCategories = questions.length;
        // Create Self/Trainer tiles for view
        for (let i = 0; i < this.numberOfCategories; ++i) {
          this.authorRow.push({ colspan: 2, text: 'Self' });
          this.authorRow.push({ colspan: 2, text: 'Trainer' });
        }
        this.COL_MAX = this.numberOfCategories * 4;
        // Get reflections for this user
        this.selfReflectionService.getReflectionsByTraineeId(this.traineeId)
          .subscribe(reflections => {
            if (reflections && reflections.length > 0) {
              console.log(reflections);
              this.trainee = reflections[0].responder;
              reflections.forEach((reflection: Reflection): void => {
                this.selfReflectionService.getReflectionQuestionsByReflectionId(reflection.id)
                  .subscribe((reflectionQuestions: ReflectionQuestion[]): void => {
                    Reflection.setReflectionQuestions(reflection, reflectionQuestions, this.numberOfCategories);
                    console.log(reflection);
                    this.reflections.push(reflection);
                    this.updateReflections();
                  });
              });
            } else {
              console.log('There are no entries for this trainee');
            }
          });
      });

  }
}
