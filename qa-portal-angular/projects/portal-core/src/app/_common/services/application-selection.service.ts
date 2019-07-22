import {Injectable} from '@angular/core';
import {Application} from '../models/application';
import {Observable, Subject} from 'rxjs';

@Injectable()
export class ApplicationSelectionService {

  private selectedApplication: Subject<Application> = new Subject();

  constructor() {
  }

  public getSelectedApplication$(): Observable<Application> {
    return this.selectedApplication.asObservable();
  }

  public setSelectedApplication(app: Application) {
    this.selectedApplication.next(app);
  }
}
