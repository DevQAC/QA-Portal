import {Observable} from 'rxjs';
import {HttpClient} from '@angular/common/http';
import {PORTAL_APPLICATIONS_API} from '../models/portal-constants';
import {Injectable} from '@angular/core';

@Injectable()
export class MenuService {

  constructor(private httpClient: HttpClient) {
  }

  getPortalMenu(): Observable<any[]> {
    return this.httpClient.get<any[]>(PORTAL_APPLICATIONS_API);
  }
}
