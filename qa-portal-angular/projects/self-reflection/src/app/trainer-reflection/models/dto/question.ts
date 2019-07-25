import { Reflection } from './reflection';
import { ReflectionQuestion } from './reflection-question';

export class Question {
  id?: number;
  body: string;
  category: string;
  forms: Reflection[];
}
