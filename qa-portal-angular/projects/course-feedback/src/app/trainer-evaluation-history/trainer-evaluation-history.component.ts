import { Component, OnInit } from '@angular/core';
import { TrainerEvaluationViewModel, TrainerEvaluationViewModel2 } from './models/trainer-evaluation-vmodel';
import { MatTab, MatTableDataSource } from '@angular/material';
import { EvaluationTableRow } from './models/evaluation-table-row';
import { EvaluationTableRow2 } from './models/evaluation-table-row2';
import { TemplateParseResult } from '@angular/compiler';
import { SearchFormComponent } from './search-form/search-form.component';

@Component({
  selector: 'app-trainer-evaluation-history',
  templateUrl: './trainer-evaluation-history.component.html',
  styleUrls: ['./trainer-evaluation-history.component.css']
})
export class TrainerEvaluationHistoryComponent implements OnInit {

  viewModel: TrainerEvaluationViewModel = new TrainerEvaluationViewModel();

  viewModel2: TrainerEvaluationViewModel2 = new TrainerEvaluationViewModel2();

  dataSource: MatTableDataSource<EvaluationTableRow>;

  dataSource2: MatTableDataSource<EvaluationTableRow2>;

  constructor(private search: SearchFormComponent) { }

  ngOnInit() {
    // back end service call to populate view model

    this.dataSource = new MatTableDataSource(this.viewModel.tableRows);
  }

  filterRows(): void{
    let str = this.search.searchForm.value;
    const tempArr = [];
    this.viewModel.tableRows.filter((r) => {
        //check if r.startDate is between the start and end date
        if (r.col1.indexOf(str) > -1) {
          tempArr.push(r);
        }
    });
    this.dataSource = new MatTableDataSource(tempArr);
    console.log("no errors yet");
  }

  showAll() {
    this.dataSource = new MatTableDataSource(this.viewModel.tableRows);
  }

}
