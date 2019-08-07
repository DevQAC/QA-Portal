import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { i } from 'i';


@Injectable({
  providedIn: 'root'
})
export class TraineeCourseEvaluationService {

  constructor(private httpClient: HttpClient) { }

  public createEvaluationForm(form: i) {
    
  }

}
