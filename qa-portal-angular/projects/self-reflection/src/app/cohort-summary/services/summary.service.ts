import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';

@Injectable()
export class SummaryService {
  constructor(private httpClient: HttpClient) {
  }

  getSummary(): Observable<any> {
    return this.httpClient.get('/self-reflection-api/reflection/summary');
  }
}
