import { User } from './user';
import { Question } from './question';
import { Base } from './base';
import { Trainee } from './trainee';
import { Trainer } from './trainer';

export class Reflection extends Base {
  id?: number;
  responder: Trainee;
  reviewer: Trainer;
  date?: Date;
  questions?: Question[];

  public static dateToDays(date: Date): number {
    return Math.floor(+date / 86400000);
  }
}
