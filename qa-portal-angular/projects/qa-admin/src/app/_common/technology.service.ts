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
    // return this.qaHttp.get({ref: 'GET_ALL_TECHNOLOGY_CATEGORIES'});

    return of([
      {
        categoryName: 'IDEs',
        technologies: [
          { technologyName: 'Visual Studio Code' },
          { technologyName: 'Eclipse' },
          { technologyName: 'IntelliJ' },
          { technologyName: 'Notepad' },
          { technologyName: 'vim' },
          { technologyName: 'emacs' },
        ]
      },
      {
        categoryName: 'Frontend Technologies',
        technologies: [
          { technologyName: 'CSS3' },
          { technologyName: 'HTML5' },
          { technologyName: 'SASS' },
          { technologyName: 'React' },
          { technologyName: 'React Native' },
          { technologyName: 'Vue' },
          { technologyName: 'Angular (2+)' },
          { technologyName: 'Angular JS' },
          { technologyName: 'Karma' },
          { technologyName: 'Jest' },
          { technologyName: 'State Management' }
        ]
      }, {
        categoryName: 'Programming Languages',
        technologies: [
          { technologyName: 'C' },
          { technologyName: 'C++' },
          { technologyName: 'C#' },
          { technologyName: 'COBOL' },
          { technologyName: 'F' },
          { technologyName: 'Go' },
          { technologyName: 'Rust' },
          { technologyName: 'Scala' },
          { technologyName: 'Java' },
          { technologyName: 'Javascript' },
          { technologyName: 'Typescript' },
          { technologyName: 'Python' },
          { technologyName: 'MATLAB' },
          { technologyName: 'FORTRAN' },
          { technologyName: 'Mornington Crescent' }
        ]
      }
    ] as TechnologyCategoryModel[]);
  }

}
