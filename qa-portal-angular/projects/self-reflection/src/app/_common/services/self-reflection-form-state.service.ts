import {SelfReflectionFormModel} from '../models/self-reflection-form-model';
import {Injectable} from '@angular/core';

@Injectable()
export class SelfReflectionFormStateService {

  isFormCompleted(selfReflectionForm: SelfReflectionFormModel) {
    return (!!selfReflectionForm.strengths &&
      !!selfReflectionForm.weaknesses &&
      !!selfReflectionForm.opportunities &&
      !!selfReflectionForm.threats &&
      this.allQuestionsAnswered(selfReflectionForm));
  }

  private allQuestionsAnswered(selfReflectionForm: SelfReflectionFormModel): boolean {
    let allQuestionsAnswered = true;
    selfReflectionForm.reflectionQuestions.forEach((question) => {
      if (!question.response) {
        allQuestionsAnswered = false;
      }
    });
    return allQuestionsAnswered;
  }

  disable(selfReflectionForm: SelfReflectionFormModel): boolean {
    return (selfReflectionForm.status === 'Reviewed' ||
           selfReflectionForm.status === 'Submitted');
  }
}
