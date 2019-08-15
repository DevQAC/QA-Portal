import { BaseModel } from './base.model';
import { TraineeModel } from './trainee.model';
import { Trainer } from './trainer.model';
import { ReflectionQuestionModel } from './reflection-question.model';

export class ReflectionModel extends BaseModel {
  id?: number;
  reviewer: Trainer;
  responder: TraineeModel;
  formDate: Date;
  reflectionQuestions: ReflectionQuestionModel[];
  trainerFeedback?: string;
  learningPathway?: string;
  strengths?: string;
  weaknesses?: string;
  opportunities?: string;
  threats?: string;
  status: string;
  // questions?: Question[];

  public static setReflectionQuestions(
    reflection: ReflectionModel, reflectionQuestions: ReflectionQuestionModel[], questionIds: number[]): void {
    reflectionQuestions = reflectionQuestions.filter(rq => rq.question && rq.question.questionCategoryName);
    for (const i of questionIds) {
      const questionIdEqualsI = (reflectionQuestion) => reflectionQuestion.question && reflectionQuestion.question.id === i;
      if (!reflectionQuestions.find(questionIdEqualsI)) {
        reflectionQuestions.push({
          id: null,
          question: {
            id: i,
            numOptions: null,
            questionCategoryName: null
          },
          response: null,
          trainerResponse: null
        });
      } else {
        // If there's multiple reflectionQuestions for a reflection, remove all but the
        // newest one.
        let rqs = reflectionQuestions.filter(questionIdEqualsI);
        if (rqs.length > 1) {
          rqs = rqs.sort((a, b): number => {
            const aVal = a.lastUpdatedTimestamp;
            const bVal = b.lastUpdatedTimestamp;
            if (aVal > bVal) {
              return 1;
            } else if (aVal < bVal) {
              return -1;
            } else {
              return 0;
            }
          });
          while (rqs.length > 1) {
            reflectionQuestions.splice(reflectionQuestions.indexOf(rqs.pop()), 1);
          }
        }
      }
    }
    reflectionQuestions.sort((a: ReflectionQuestionModel, b: ReflectionQuestionModel): number => {
      const aCategory = a.question.questionCategoryName;
      const bCategory = b.question.questionCategoryName;
      if (aCategory < bCategory) {
        return -1;
      } else if (aCategory > bCategory) {
        return 1;
      } else {
        return 0;
      }
    });

    reflection.reflectionQuestions = reflectionQuestions;
  }
}
