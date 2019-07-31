import { Reflection } from './reflection';
import { Question } from './question';
import { Base } from './base';

export class ReflectionQuestion extends Base {
  id?: number;
  // reflection: Reflection;
  question: Question;
  response?: number;
  trainerResponse?: number;
}
