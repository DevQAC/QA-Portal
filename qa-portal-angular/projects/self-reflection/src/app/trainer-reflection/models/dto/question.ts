import { Reflection } from './reflection';
import { Base } from './base';

export class Question extends Base {
  id?: number;
  body: string;
  category: string;
  forms: Reflection[];
}
