import { Component, HostListener, OnInit, OnDestroy } from '@angular/core';
import { MatPaginator } from '@angular/material/paginator';
import { MatTableDataSource } from '@angular/material/table';
import { CVSearchHistoryService } from './services/cv-search-history.service';
import { CVSearchHistoryModel } from './models/cv-search-history-model';
import { CVSearchModel } from './models/cv-search-model';
import { Subscription } from 'rxjs';
import { QaErrorHandlerService } from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import { ViewChild } from '@angular/core'

@Component({
  selector: 'app-cv-search',
  templateUrl: './cv-search.component.html',
  styleUrls: ['./cv-search.component.css']
})
export class CvSearchComponent implements OnInit, OnDestroy {

  historyViewModel = new CVSearchHistoryModel();
  displayedColumns: string[] = ['name', 'intake', 'tech', 'status', 'clients'];
  dataSource: MatTableDataSource<CVSearchModel>;
  currentFormDateSource: MatTableDataSource<CVSearchModel>;
  currentForm: CVSearchModel[] =[];
  today = new Date().getDay();
  upcomingFriday = new Date();
  loadingData = true;
  cvSearchSubscription: Subscription;
  searchText: string = '';
  previous: string;

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private cvSearchHistoryService: CVSearchHistoryService,
    private errorHandlerService: QaErrorHandlerService) {
  }
  // @HostListener('input') oninput() {
  //   this.searchItems();
  // }
  ngOnInit() {
    this.cvSearchSubscription = this.cvSearchHistoryService.getAdminSearches().subscribe(
      (response) => {
        response.forEach((search) => {
          // if (search.status !== "Reviewed") {
            // this.currentForm = [];
            this.currentForm.push(search);
          // } else {
            this.historyViewModel.cvSearches.push(search);
          // }
        });
        this.loadingData = false;
        this.dataSource = new MatTableDataSource<CVSearchModel>(this.historyViewModel.cvSearches);
        this.dataSource.paginator = this.paginator;
        this.currentFormDateSource = new MatTableDataSource<CVSearchModel>(this.currentForm);
        this.currentFormDateSource.paginator = this.paginator;
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );
  }

  applyFilter(filterValue: string) {
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.currentFormDateSource.filter = filterValue;
  }
  
  ngOnDestroy() {
    this.cvSearchSubscription.unsubscribe();
  }

  getCVSearchUrl(searchId: string) {
    return '/qa/portal/training/trainee/selfreflection/' + searchId;
  }

}
