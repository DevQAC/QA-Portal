import { Reflection } from './reflection';
import { Question } from './question';

export class ReflectionQuestion {
  id?: number;
  reflection: Reflection;
  question: Question;
  response?: number;
  trainerResponse?: number;
}
