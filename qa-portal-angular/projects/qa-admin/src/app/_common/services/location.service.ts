import { Injectable } from '@angular/core';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { Observable } from 'rxjs';
import { LocationModel } from 'projects/portal-core/src/app/_common/models/location.model';

@Injectable({
  providedIn: 'root'
})
export class LocationService {

  constructor(public qaHttp: QaHttpService) { }

  public getAllLocations(): Observable<LocationModel[]> {
    return this.qaHttp.get({ ref: 'GET_ALL_LOCATIONS' });
  }

  public getLocationById(id: string | number): Observable<LocationModel> {
    return this.qaHttp.get({ ref: 'GET_LOCATION_BY_ID', params: { id: id.toString() } });
  }

  public saveLocation(location: LocationModel): Observable<LocationModel> {
    return this.qaHttp.put({ ref: 'SAVE_LOCATION' }, location);
  }

  public addLocation(location: LocationModel): Observable<LocationModel> {
    return this.qaHttp.post({ ref: 'CREATE_LOCATION' }, location);
  }
}
