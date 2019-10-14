import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';

@Injectable({
  providedIn: 'root'
})
export class FormService {

  constructor(private qaHttp: QaHttpService) { }

  public getAllForms(): Observable<any[]> {
    return this.qaHttp.get({ref: 'GET_ALL_FORMS'});
  }

  public addForm(form: any): Observable<any> {
    return this.qaHttp.post({ref: 'CREATE_FORM'}, form);
  }
}
