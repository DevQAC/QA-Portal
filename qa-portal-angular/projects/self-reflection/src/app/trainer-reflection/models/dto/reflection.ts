import { User } from './user';
import { Question } from './question';
import { Base } from './base';

export class Reflection extends Base {
  id?: number;
  responder: User;
  reviewer: User;
  date?: Date;
  questions?: Question[];

  public static dateToDays(date: Date): number {
    return Math.floor(+date / 86400000);
  }
}
