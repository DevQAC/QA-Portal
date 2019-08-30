import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {CVSearchHistoryService} from './services/cv-search.service';
import {CVSearchFilterService} from './services/cv-search-filter.service';
import {CVSearchModel} from './models/cv-search-model';
import {Subscription} from 'rxjs';
import {QaErrorHandlerService} from '../../../../portal-core/src/app/_common/services/qa-error-handler.service';
import {FormControl} from '@angular/forms';

@Component({
  selector: 'app-cv-search',
  templateUrl: './cv-search.component.html',
  styleUrls: ['./cv-search.component.css']
})
export class CvSearchComponent implements OnInit, OnDestroy {

  // historyViewModel = new CVSearchHistoryModel();
  displayedColumns: string[] = ['name', 'intake', 'tech', 'status', 'clients'];
  technology: string[] = [""];
  intake: string[];
  status: string[];
  selectedIntake : string = "";
  selectedTech : string = "";
  selectedStatus : string = "";
  dataSource: MatTableDataSource<CVSearchModel>;
  currentFormDateSource: MatTableDataSource<CVSearchModel>;
  currentForm: CVSearchModel[] = [];

  loadingData = true;
  cvSearchSubscription: Subscription;
  filterSubscription: Subscription;
  searchText: string = '';
  previous: string;
  filteredData: CVSearchModel = { id: 0, name: '', intake: '', tech: '', clients: [], status: '' };
  globalFIlter = '';
  techFilter = new FormControl();

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;

  constructor(
    private cvSearchFilterService: CVSearchFilterService,
    private cvSearchHistoryService: CVSearchHistoryService,
    private errorHandlerService: QaErrorHandlerService) {
  }
  // @HostListener('input') oninput() {
  //   this.searchItems();
  // }
  ngOnInit() {

    this.filterSubscription = this.cvSearchFilterService.getFilters().subscribe(
      (response) => {
        this.technology = response.technology;
        this.intake = response.cohort;
        this.status = response.cvStatus;

        this.loadingData = false;
        this.dataSource = new MatTableDataSource<CVSearchModel>(this.currentForm);
        this.dataSource.paginator = this.paginator;

        // To filter by just name
        // this.currentFormDateSource.filterPredicate = (data: CVSearchModel, filter: string) => {
        //   return data.name == filter;
        //  };
        // this.techFilter.valueChanges.subscribe((techFilterValue) => {
        //   this.filteredData['tech'] = techFilterValue;
        //   this.dataSource.filter = JSON.stringify(this.filteredData);
        // });

        this.dataSource.filterPredicate = this.customFilterPredicate();
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );
  }

  getSearch(term: string, intakeChoice:string = "", techChoice:string = "", statusChoice:string = "" ) {
    console.log(term);
    intakeChoice = this.selectedIntake;
    techChoice = this.selectedTech;
    statusChoice = this.selectedStatus;
    this.cvSearchSubscription = this.cvSearchHistoryService.getCVSearches(term, intakeChoice, techChoice, statusChoice).subscribe(
      (response) => {
        this.currentForm = [];
        response.forEach((search) => {
          // if (search.status !== "Reviewed") {

          this.currentForm.push(search);
          // } else {
          // this.historyViewModel.cvSearches.push(search);
          // }
        });
        this.loadingData = false;
        this.dataSource = new MatTableDataSource<CVSearchModel>(this.currentForm);
        this.dataSource.paginator = this.paginator;

        // To filter by just name
        // this.currentFormDateSource.filterPredicate = (data: CVSearchModel, filter: string) => {
        //   return data.name == filter;
        //  };

        // this.techFilter.valueChanges.subscribe((techFilterValue) => {
        //   this.filteredData['tech'] = techFilterValue;
        //   this.dataSource.filter = JSON.stringify(this.filteredData);
        // });

        this.dataSource.filterPredicate = this.customFilterPredicate();
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );

  }

  applyFilter(filterValue: string) {
    // this.currentFormDateSource = new MatTableDataSource<CVSearchModel>(this.currentForm);
    //     this.currentFormDateSource.paginator = this.paginator;
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;
    // this.filteredData = this.currentFormDateSource.filteredData;
    // this.currentFormDateSource = new MatTableDataSource<CVSearchModel>(this.filteredData);
    //   this.currentFormDateSource.paginator = this.paginator;

  }

  applyFilter1(filter) {
    this.globalFIlter = filter;
    this.dataSource.filter = JSON.stringify(this.filteredData)
  }

  customFilterPredicate() {
    const myFilterPredicate = (data: CVSearchModel, filter: string): boolean => {
      let globalMatch = !this.globalFIlter;


      console.log(this.globalFIlter);

      if (this.technology.includes(this.globalFIlter)) {
        // search tech text fields
        globalMatch = data.tech.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      }

      else if (this.intake.includes(this.globalFIlter)) {
        // search tech text fields
        globalMatch = data.intake.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      }

      else if (this.status.includes(this.globalFIlter)) {
        // search tech text fields
        globalMatch = data.status.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      }

      else {
        // search name text fields
        globalMatch = data.name.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      }

      if (!globalMatch) {
        return;
      }

      console.log(globalMatch);
      if (this.technology.includes(this.globalFIlter)) {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.tech.toString().trim().indexOf(searchString.tech) !== -1;
      }

      else if (this.intake.includes(this.globalFIlter)) {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.intake.toString().trim().indexOf(searchString.intake) !== -1;
      }

      else if (this.status.includes(this.globalFIlter)) {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.status.toString().trim().indexOf(searchString.status) !== -1;
      }
      else  {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.name.toString().trim().indexOf(searchString.name) !== -1;
      }
    }
    return myFilterPredicate;
  }

  ngOnDestroy() {
    this.cvSearchSubscription.unsubscribe();
  }

  getCVSearchUrl(searchId: string) {
    return '/test';
  }

}
