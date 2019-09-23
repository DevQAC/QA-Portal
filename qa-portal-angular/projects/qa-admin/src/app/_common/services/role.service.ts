import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {take} from 'rxjs/operators';
import {GET_PORTAL_ROLES_URL} from '../models/user.constant';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private http: HttpClient) {
  }

  public getPortalRoles(): Observable<string[]> {
    return this.http.get<string[]>(GET_PORTAL_ROLES_URL).pipe(
      take(1)
    );
  }
}
