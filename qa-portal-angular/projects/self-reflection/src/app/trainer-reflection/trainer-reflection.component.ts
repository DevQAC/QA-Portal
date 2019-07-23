import { Component, OnInit } from '@angular/core';
import { SkillArea } from './models/skill-area';
import { Tile } from './models/tileRow';
import { User } from './models/User.enum';
import { TraineeReflectionService } from './services/trainee-reflection.service';

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
  constructor(private traineeReflectionService: TraineeReflectionService) {
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
    this.traineeReflectionService.getTraineeReflection().subscribe((skillAreas) => this.skillAreas = skillAreas);
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
