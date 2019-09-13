import {IFormModel} from '../../../../../qa-forms/src/app/_common/models';
import {Observable} from 'rxjs';

export interface IFormService {
  getForm(courseId: string): Observable<IFormModel>;

  createForm(form: IFormModel): Observable<IFormModel>;

  updateForm(form: IFormModel): Observable<IFormModel>;
}
