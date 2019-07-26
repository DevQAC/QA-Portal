import { User } from './user';
import { Question } from './question';
import { Base } from './base';
import { Trainee } from './trainee';
import { Trainer } from './trainer';
import { ReflectionQuestion } from './reflection-question';

export class Reflection extends Base {
  id?: number;
  reflectionQuestions: ReflectionQuestion[];
  reviewer: Trainer;
  responder: Trainee;
  formDate: Date;
  trainerComments?: string;
  learningPathway?: string;
  questions?: Question[];

  public static dateToDays(date: Date): number {
    return Math.floor(+date / 86400000);
  }
}
