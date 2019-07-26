import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {PORTAL_APPLICATIONS_API} from '../../../../../portal-core/src/app/_common/models/portal-constants';

@Injectable()
export class ConfigService {
  constructor(private httpClient: HttpClient) {
  }

  getSummary() {
    return this.httpClient.get('/self-reflection-api/reflection/summary');
  }
}
