import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { FormModel } from 'projects/portal-core/src/app/_common/models/form.model';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  constructor(private qaHttp: QaHttpService) { }

  public getAllForms(): Observable<FormModel[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_FORMS' });
  }

  public addForm(form: any): Observable<FormModel> {
    return this.qaHttp.post({ ref: 'CREATE_FORM' }, form);
  }

  public getFormById(id: string | number): Observable<FormModel> {
    return this.qaHttp.get({ ref: 'GET_FORM_BY_ID', params: { id: id.toString() } });
  }

  public saveForm(form: FormModel): Observable<FormModel> {
    return this.qaHttp.put({ ref: 'SAVE_FORM' }, form);
  }
}
