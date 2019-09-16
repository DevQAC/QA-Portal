import { ReflectionQuestionModel } from './dto/reflection-question.model';

export class RowData {
  category: string;
  questions?: QuestionData[];
  authors?: string[];
}

export class QuestionData {
  id: number;
  body: string;
  reflectionQuestions: ReflectionQuestionModel[];
}
