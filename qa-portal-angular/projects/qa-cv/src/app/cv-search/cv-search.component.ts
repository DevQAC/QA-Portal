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

  displayedColumns: string[] = ['name', 'intake', 'tech', 'status', 'clients'];
  technology: string[] = [''];
  intake: string[];
  status: string[];
  selectedIntake: string = '';
  selectedTech: string = '';
  selectedStatus: string = '';
  dataSource: MatTableDataSource<CVSearchModel>;
  currentFormDateSource: MatTableDataSource<CVSearchModel>;
  currentForm: CVSearchModel[] = [];

  loadingData = true;
  cvSearchSubscription: Subscription;
  filterSubscription: Subscription;
  searchText: string = '';
  previous: string;
  filteredData: CVSearchModel = {id: 0, fullName: '', cohort: '', allSkills: [], clients: [], status: ''};
  globalFIlter = '';
  techFilter = new FormControl();
  countTech: number;
  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(
    private cvSearchFilterService: CVSearchFilterService,
    private cvSearchHistoryService: CVSearchHistoryService,
    private errorHandlerService: QaErrorHandlerService) {
  }


  ngOnInit() {

    this.filterSubscription = this.cvSearchFilterService.getFilters().subscribe(
      (response) => {
        this.technology = response.technology;
        this.intake = response.cohort;
        this.status = response.cvStatus;

        this.loadingData = false;
        this.dataSource = new MatTableDataSource<CVSearchModel>(this.currentForm);
        this.dataSource.paginator = this.paginator;


        this.dataSource.filterPredicate = this.customFilterPredicate();
        this.loadingData = false;
      },
      (error) => {
        this.loadingData = false;
        this.errorHandlerService.handleError(error);
      }
    );
  }

  getSearch(term: string, intakeChoice: string = '', techChoice: string = '', statusChoice: string = '') {
    intakeChoice = this.selectedIntake;
    techChoice = this.selectedTech;
    statusChoice = this.selectedStatus;
    this.cvSearchSubscription = this.cvSearchHistoryService.searchCVs(term, intakeChoice, techChoice, statusChoice).subscribe(
      (response) => {
        this.currentForm = [];
        response.forEach((search) => {
          this.currentForm.push(search);
          // this.countTech = this.currentForm[0].allSkills[0].programmingLanguages.length+this.currentForm[0].allSkills[0].ides.length+this.currentForm[0].allSkills[0].operatingSystems.length+
          // this.currentForm[0].allSkills[0].devops.length+this.currentForm[0].allSkills[0].databases.length+this.currentForm[0].allSkills[0].platforms.length+
          // this.currentForm[0].allSkills[0].other.length
          //
          // console.log("number of skills: "+this.countTech);
        });
        this.loadingData = false;
        this.dataSource = new MatTableDataSource<CVSearchModel>(this.currentForm);
        this.dataSource.paginator = this.paginator;
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
    filterValue = filterValue.trim(); // Remove whitespace
    filterValue = filterValue.toLowerCase(); // MatTableDataSource defaults to lowercase matches
    this.dataSource.filter = filterValue;


  }

  applyFilter1(filter) {
    this.globalFIlter = filter;
    this.dataSource.filter = JSON.stringify(this.filteredData);
  }

  customFilterPredicate() {
    const myFilterPredicate = (data: CVSearchModel, filter: string): boolean => {
      let globalMatch = !this.globalFIlter;


      console.log(this.globalFIlter);

      if (this.technology.includes(this.globalFIlter)) {
        // search tech text fields
        globalMatch = data.allSkills.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      } else if (this.intake.includes(this.globalFIlter)) {
        // search tech text fields
        globalMatch = data.cohort.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      } else if (this.status.includes(this.globalFIlter)) {
        // search tech text fields
        globalMatch = data.status.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      } else {
        // search name text fields
        globalMatch = data.fullName.toString().trim().toLowerCase().indexOf(this.globalFIlter.toLowerCase()) !== -1;
      }

      if (!globalMatch) {
        return;
      }

      console.log(globalMatch);
      if (this.technology.includes(this.globalFIlter)) {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.allSkills.toString().trim().indexOf(searchString.tech) !== -1;
      } else if (this.intake.includes(this.globalFIlter)) {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.cohort.toString().trim().indexOf(searchString.intake) !== -1;
      } else if (this.status.includes(this.globalFIlter)) {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.status.toString().trim().indexOf(searchString.status) !== -1;
      } else {
        let searchString = JSON.parse(filter);
        console.log(searchString);
        return data.fullName.toString().trim().indexOf(searchString.name) !== -1;
      }
    };
    return myFilterPredicate;
  }

  ngOnDestroy() {
    if (!!this.cvSearchSubscription) {
      this.cvSearchSubscription.unsubscribe();
    }
  }

  getCVSearchUrl(searchId: string) {
    return '/test';
  }

}
