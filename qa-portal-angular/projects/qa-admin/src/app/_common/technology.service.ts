import { Injectable } from '@angular/core';
import { QaHttpService } from 'projects/portal-core/src/app/_common/services/qa-http.service';
import { Observable, of } from 'rxjs';
import { TechnologyCategoryModel } from 'projects/portal-core/src/app/_common/models/technology-category.model';

@Injectable({
  providedIn: 'root'
})
export class TechnologyService {

  constructor(private qaHttp: QaHttpService) { }

  public getAllCategories(): Observable<TechnologyCategoryModel[]> {
    return this.qaHttp.get({ref: 'GET_ALL_TECHNOLOGY_CATEGORIES'});
  }


  public getCategoryById(id: string | number): Observable<TechnologyCategoryModel> {
    return this.qaHttp.get({ref: 'GET_ALL_TECHNOLOGY_CATEGORY_BY_ID', params: {id: id.toString()}});
  }

}
