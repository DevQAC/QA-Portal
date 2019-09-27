import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs';
import {ReflectionModel} from '../models/dto/reflection.model';
import {take} from 'rxjs/operators';

@Injectable()
export class TrainerReflectionService {

  constructor(private httpClient: HttpClient) {
  }

  public getSelfReflectionsForTraineeDescendingDate(traineeId: number): Observable<ReflectionModel[]> {
    return this.httpClient.get<ReflectionModel[]>('self-reflection-api/reflection/trainee/' + traineeId).pipe(
      take(1)
    );
  }

  public saveSelfReflectionForm(selfReflectionForm: ReflectionModel): Observable<ReflectionModel> {
    return this.httpClient.put<ReflectionModel>('self-reflection-api/reflection', selfReflectionForm).pipe(
      take(1)
    );
  }
}
