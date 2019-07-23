import { Component, OnInit } from '@angular/core';
import { SkillArea } from './models/skill-area';
import { Tile } from './models/tileRow';
import { FormsModule } from '@angular/forms';
import { UserScore } from './models/user-score';
import { User } from './models/User.enum';
import { from } from 'rxjs';

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
  constructor() {
    console.log(this.transpose([[1, 2, 3], [4, 5, 6]]));
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
    const dates = [new Date(5), new Date(4), new Date(3), new Date(2), new Date(1)];
    this.skillAreas = [
      {
        name: 'Technical Skills',
        forms: [
          {
            id: 1,
            text: 'How well have you been able to use the technologies and tools you have learnt this week to solve a solution?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 8, weekCommencing: dates[0] },
                  { value: 7, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 5, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              }]
          },
          {
            id: 2,
            text: 'How well would you be able to explain the concepts you have learnt this week to a peer?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 8, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              },
              {
                author: User.TRAINER,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 7, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 8, weekCommencing: dates[2] },
                  { value: 8, weekCommencing: dates[1] },
                ]
              }]
          }
        ]
      },
      {
        name: 'Soft Skills',
        forms: [
          {
            id: 3,
            text: 'How well have you driven high standards through collaboration and teamwork this week?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 6, weekCommencing: dates[5] },
                  { value: 7, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              }]
          },
          {
            id: 4,
            text: 'How well have you been able to present ideas and concepts to the group this week?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 6, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 7, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              },
              {
                author: User.TRAINER,
                scores: [
                  { value: 5, weekCommencing: dates[5] },
                  { value: 5, weekCommencing: dates[4] },
                  { value: 6, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 6, weekCommencing: dates[1] },
                ]
              }]
          }
        ]
      },
      {
        name: 'Attitude',
        forms: [
          {
            id: 5,
            text: 'How well have you managed your time this week at the Academy?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 8, weekCommencing: dates[5] },
                  { value: 9, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 7, weekCommencing: dates[2] },
                  { value: 8, weekCommencing: dates[1] },
                ]
              }]
          },
          {
            id: 6,
            text: 'How ambitious have you been to work on projects out of the Academy to improve your skills?',
            userScores:
              [{
                author: User.TRAINEE,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 9, weekCommencing: dates[4] },
                  { value: 8, weekCommencing: dates[3] },
                  { value: 7, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              },
              {
                author: User.TRAINER,
                scores: [
                  { value: 7, weekCommencing: dates[5] },
                  { value: 10, weekCommencing: dates[4] },
                  { value: 7, weekCommencing: dates[3] },
                  { value: 6, weekCommencing: dates[2] },
                  { value: 7, weekCommencing: dates[1] },
                ]
              }]
          }
        ]
      }
    ];

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
    console.log(this.scoreRow);
  }

}
