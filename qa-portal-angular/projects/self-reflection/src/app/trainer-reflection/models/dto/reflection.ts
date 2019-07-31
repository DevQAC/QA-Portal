import { Base } from './base';
import { Trainee } from './trainee';
import { Trainer } from './trainer';
import { ReflectionQuestion } from './reflection-question';

export class Reflection extends Base {
  id?: number;
  reviewer: Trainer;
  responder: Trainee;
  formDate: Date;
  reflectionQuestions: ReflectionQuestion[];
  trainerFeedback?: string;
  learningPathway?: string;
  strengths?: string;
  weaknesses?: string;
  opportunities?: string;
  threats?: string;
  // questions?: Question[];

  public static setReflectionQuestions(
    reflection: Reflection, reflectionQuestions: ReflectionQuestion[], questionIds: number[]): void {
    reflectionQuestions = reflectionQuestions.filter(rq => rq.question && rq.question.category);
    for (const i of questionIds) {
      const questionIdEqualsI = (reflectionQuestion) => reflectionQuestion.question && reflectionQuestion.question.id === i;
      if (!reflectionQuestions.find(questionIdEqualsI)) {
        reflectionQuestions.push({
          id: null,
          question: {
            id: i,
            numOptions: null
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
              return -1;
            } else if (aVal < bVal) {
              return 11;
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
    reflectionQuestions.sort((a: ReflectionQuestion, b: ReflectionQuestion): number => {
      const aCategory = a.question.category;
      const bCategory = b.question.category;
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
