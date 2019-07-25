import { Component, OnInit } from '@angular/core';
// import { CdkTextareaAutosize } from '@angular/cdk/text-field';
import { SkillArea } from './models/skill-area';
import { Tile } from './models/tileRow';
import { User } from './models/User.enum';
import { SelfReflectionService } from './services/self-reflection.service';
import { Reflection } from './models/dto/reflection';
import { Trainee } from './models/dto/trainee';

@Component({
  selector: 'app-trainer-reflection',
  templateUrl: './trainer-reflection.component.html',
  styleUrls: ['./trainer-reflection.component.css']
})
export class TrainerReflectionComponent implements OnInit {

  public skillAreas: SkillArea[] = [];
  public titleRow: Tile[] = [];
  public questionRow: Tile[] = [];
  public authorRow: Tile[] = [];
  public scoreRow: Tile[] = [];
  public COL_MAX = 24;
  public firstname = '';
  public surname = '';
  public trainerComments = '';
  public learningPathway = '';

  constructor(private traineeReflectionService: SelfReflectionService) {
    this.trainerComments = 'Ea qui ipsum sint nisi et sunt et eu commodo proident id.' +
      'Exercitation adipisicing ut aute consequat pariatur minim duis cupidatat velit quis. Qui ' +
      'consectetur reprehenderit nisi deserunt adipisicing velit enim quis cillum eiusmod. Minim ea mollit in ' +
      'eu tempor tempor quis. Veniam enim voluptate est do velit aute.';
  }

  private getUserTitle(user: User): string {
    switch (user) {
      case User.TRAINEE:
        return 'Self';
      case User.TRAINER:
        return 'Trainer';
      default:
        return 'Undefined';
    }
  }

  private transpose(arrays: any[][]): any[][] {
    const outerLen = arrays.length;
    if (outerLen < 1) {
      return [];
    }
    const innerLen = arrays[0].length;
    const output = [];
    // tslint:disable-next-line: prefer-for-of
    for (let i = 0; i < innerLen; ++i) {
      const inner = [];
      // tslint:disable-next-line: prefer-for-of
      for (let j = 0; j < outerLen; ++j) {
        inner.push(arrays[j][i]);
      }
      output.push(inner);
    }
    return output;
  }

  ngOnInit() {
    const randomId = () => Math.floor(Math.random() * Math.pow(10, 7));
    const fakeReflection: Reflection = {
      responder: { id: randomId(), userName: 'David' },
      reviewer: { id: randomId(), userName: 'Alan' },
      date: new Date(),
      questions: []
    };
    // this.traineeReflectionService.create(fakeReflection)
    //   .subscribe(data => console.log(data), error => console.log(error));
    // this.traineeReflectionService.getCurrent().subscribe(data => console.log(data), error => console.log(error));
    this.traineeReflectionService.getById(3).subscribe(data => console.log(data), error => console.log(error));
    this.traineeReflectionService.getTraineeReflection().subscribe((skillAreas) => this.skillAreas = skillAreas);
    this.firstname = 'Firstname';
    this.surname = 'Surname';
    const titleColSpan = this.COL_MAX / this.skillAreas.length;
    let questionColSpan;
    let authorColSpan;
    let innerScores;
    const outerScores = [];
    for (const skillArea of this.skillAreas) {
      this.titleRow.push({ text: skillArea.name, colspan: titleColSpan });
      questionColSpan = titleColSpan / skillArea.forms.length;
      for (const form of skillArea.forms) {
        this.questionRow.push({ text: form.text, colspan: questionColSpan });
        authorColSpan = questionColSpan / form.userScores.length;
        for (const userScore of form.userScores) {
          this.authorRow.push({ text: this.getUserTitle(userScore.author), colspan: authorColSpan });
          innerScores = [];
          for (const score of userScore.scores) {
            innerScores.push({ text: score.value.toString(), colspan: authorColSpan });
          }
          outerScores.push(innerScores);
        }
      }
    }
    this.scoreRow = this.transpose(outerScores).reduce((prev, curr) => prev.concat(curr), []);
  }

}
