import {Injectable} from '@angular/core';
import {Application} from '../models/application';
import {Observable, Subject} from 'rxjs';
import { DepartmentApplications } from '../models/department-applications';

@Injectable()
export class ApplicationSelectionService {

  private selectedApplication: Subject<Application> = new Subject();
  private selectedDepartment: Subject<DepartmentApplications> = new Subject();

  constructor() {
  }

  public getSelectedApplication$(): Observable<Application> {
    return this.selectedApplication.asObservable();
  }

  public setSelectedApplication(app: Application) {
    this.selectedApplication.next(app);
  }

  public getSelectedDepartment$(): Observable<DepartmentApplications> {
    return this.selectedDepartment.asObservable();
  }

  public setSelectedDepartment(dep: DepartmentApplications) {
    this.selectedDepartment.next(dep);
    console.log('DEP', dep);
  }
}
