import { User } from './user';
import { Question } from './question';
import { Trainer } from './trainer';
import { Trainee } from './trainee';

export class Reflection {
  id?: number;
  responder: User;
  reviewer: User;
  date: Date;
  questions: Question[];
}
