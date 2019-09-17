import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {ReflectionHistoryService} from './services/reflection-history.service';
import {SelfReflectionHistoryModel} from './models/self-reflection-history.model';
import {SelfReflectionFormModel} from '../_common/models/self-reflection-form-model';
import {Subscription} from 'rxjs';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-self-reflection-history',
  templateUrl: './self-reflection-history.component.html',
  styleUrls: ['./self-reflection-history.component.css']
})
export class SelfReflectionHistoryComponent implements OnInit, OnDestroy {
  historyViewModel = new SelfReflectionHistoryModel();
  displayedColumns: string[] = ['content', 'date', 'status'];
  dataSource: MatTableDataSource<SelfReflectionFormModel>;
  currentFormDateSource: MatTableDataSource<SelfReflectionFormModel>;
  currentForm: SelfReflectionFormModel[];
  today = new Date().getDay();
  upcomingFriday = new Date();
  loadingData = true;
  reflectionSubscription: Subscription;


  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private reflectionHistoryService: ReflectionHistoryService,
              private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.reflectionSubscription = this.reflectionHistoryService.getTraineeReflections().subscribe(
      (response) => {
        response.forEach((selfReflection) => {
          if (selfReflection.status !== 'Reviewed') {
            this.currentForm = [];
            this.currentForm.push(selfReflection);
          } else {
            this.historyViewModel.selfReflections.push(selfReflection);
          }
        });
        this.dataSource = new MatTableDataSource<SelfReflectionFormModel>(this.historyViewModel.selfReflections);
        this.dataSource.paginator = this.paginator;
        this.currentFormDateSource = new MatTableDataSource<SelfReflectionFormModel>(this.currentForm);
        this.currentFormDateSource.paginator = this.paginator;
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );
  }

  ngOnDestroy() {
    this.reflectionSubscription.unsubscribe();
  }

  getReflectionUrl(reflectionId: string) {
    return '/qa/portal/training/self-reflection/trainee/' + reflectionId;
  }
}
