import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {ReflectionHistoryService} from './services/reflection-history.service';
import {SelfReflectionHistoryModel} from './models/self-reflection-history.model';
import {SelfReflectionFormModel} from "../_common/models/self-reflection-form-model";
import {Subscription} from "rxjs";
import {QaErrorHandlerService} from "../../../../portal-core/src/app/_common/services/qa-error-handler.service";

@Component({
  selector: 'app-self-reflection-history',
  templateUrl: './self-reflection-history.component.html',
  styleUrls: ['./self-reflection-history.component.css']
})
export class SelfReflectionHistoryComponent implements OnInit {
  historyViewModel = new SelfReflectionHistoryModel();
  displayedColumns: string[] = ['content', 'date'];
  dataSource: MatTableDataSource<SelfReflectionFormModel>;
  today = new Date().getDay();
  upcomingFriday = new Date();
  loadingData = true;
  reflectionSubscription: Subscription;

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private reflectionHistoryService: ReflectionHistoryService, private errorHandlerService: QaErrorHandlerService) {
  }

  ngOnInit() {
    this.reflectionSubscription = this.reflectionHistoryService.getTraineeReflections().subscribe(
      (response) => {
        response.forEach((selfReflection) => {
          this.historyViewModel.selfReflections.push(selfReflection);

        });
        this.loadingData = false;
        console.log(this.historyViewModel.selfReflections);
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    )


    this.dataSource = new MatTableDataSource<SelfReflectionFormModel>(this.historyViewModel.selfReflections);
    this.dataSource.paginator = this.paginator;
    this.loadingData = false;
  }

}
