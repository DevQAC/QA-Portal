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
    reflectionQuestions.filter(rq => rq.question && rq.question.category);
    for (const i of questionIds) {
      if (!reflectionQuestions.find((reflectionQuestion) => reflectionQuestion.question && reflectionQuestion.question.id === i)) {
        reflectionQuestions.push({
          id: null,
          reflection,
          question: {
            id: i,
            numOptions: null
          },
          response: null,
          trainerResponse: null
        });
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
