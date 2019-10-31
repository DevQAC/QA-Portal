import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { RoleModel } from 'projects/portal-core/src/app/_common/models/role.model';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private qaHttp: QaHttpService) { }

  public getPortalRoleNames(): Observable<string[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_ROLE_NAMES' });
  }

  public getPortalRoles(): Observable<RoleModel[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_ROLES' });
  }

  public getRoleById(id: string | number): Observable<RoleModel> {
    return this.qaHttp.get({ ref: 'GET_ROLE_BY_ID', params: { id: id.toString() } });
  }

  public saveRole(role: RoleModel): Observable<RoleModel> {
    return this.qaHttp.put({ ref: 'SAVE_ROLE' }, role);
  }

  public addRole(role: RoleModel): Observable<RoleModel> {
    return this.qaHttp.post({ ref: 'CREATE_ROLE' }, role);
  }
}
