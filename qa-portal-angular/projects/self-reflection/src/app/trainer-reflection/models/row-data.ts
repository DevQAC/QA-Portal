import { ReflectionQuestion } from './dto/reflection-question';

export class RowData {
  category: string;
  questions?: QuestionData[];
  authors?: string[];
}

export class QuestionData {
  id: number;
  body: string;
  reflectionQuestions: ReflectionQuestion[];
}
