import { Component, OnInit } from '@angular/core';
import { TrainerEvaluationViewModel, TrainerEvaluationViewModel2 } from './models/trainer-evaluation-vmodel';
import { MatTab, MatTableDataSource } from '@angular/material';
import { EvaluationTableRow } from './models/evaluation-table-row';
import { EvaluationTableRow2 } from './models/evaluation-table-row2';
import { TemplateParseResult } from '@angular/compiler';
import { SearchFormComponent } from './search-form/search-form.component';
import { FormGroup, FormControl, Form } from '@angular/forms';
import { getLocaleDateFormat } from '@angular/common';
import {RetrieveTrainerEvaluationHistoryService} from './services/retrieve-trainer-evaluation-history.service';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-trainer-evaluation-history',
  templateUrl: './trainer-evaluation-history.component.html',
  styleUrls: ['./trainer-evaluation-history.component.css']
})
export class TrainerEvaluationHistoryComponent implements OnInit {

  viewModel: TrainerEvaluationViewModel = new TrainerEvaluationViewModel();

  viewModel2: TrainerEvaluationViewModel = new TrainerEvaluationViewModel();

  viewModel3: TrainerEvaluationViewModel2 = new TrainerEvaluationViewModel2();

  dataSource: MatTableDataSource<EvaluationTableRow>;

  dataSource2: MatTableDataSource<EvaluationTableRow>

  dataSource3: MatTableDataSource<EvaluationTableRow2>;


  constructor(private retrieveTrainerEvalHistory: RetrieveTrainerEvaluationHistoryService,
    private errorHandler: QaErrorHandlerService) { }

  searching: FormControl = new FormControl();

  filter: string = "Show All";


  ngOnInit() {
    // need to call RetrieveTrainerEvaluationHistoryService
    this.retrieveTrainerEvalHistory.getEvalHistory().subscribe( 
      (response) => {
          console.log(JSON.stringify(response));
          //populate MatTableDataSource for your tables on the page
      },
      (error) => {
        this.errorHandler.handleError(error);
      }
    );
    this.viewModel2.tableRows = [{
      col1: "hello", col2: new Date("2019-08-08").getMonth()+1, col3: new Date("2019-08-09"), col4: "1623", col5: "confi"
  }];

    this.dataSource2 = new MatTableDataSource(this.viewModel2.tableRows);


  }

  filterRows(): void{
    let str= this.searching.value;
    let tempArr = [];
    this.viewModel2.tableRows.filter((r) => {
        // check if r.startDate is between the start and end date
        if (!str) {
          tempArr = this.viewModel2.tableRows;
        }
        else if (r.col1.indexOf(str) > -1) {
          tempArr.push(r);
        }
    });
    this.dataSource2 = new MatTableDataSource(tempArr);
  }

  showAll() {
    this.dataSource2 = new MatTableDataSource(this.viewModel2.tableRows);
  }
}
