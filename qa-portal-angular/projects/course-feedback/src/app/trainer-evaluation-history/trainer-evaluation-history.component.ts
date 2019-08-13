import {Component, OnInit} from '@angular/core';
import {TrainerEvaluationViewModel, TrainerEvaluationViewModel2} from './models/trainer-evaluation-vmodel';
import {MatTableDataSource} from '@angular/material';
import {EvaluationTableRow} from './models/evaluation-table-row';
import {EvaluationTableRow2} from './models/evaluation-table-row2';
import {FormControl} from '@angular/forms';

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

  dataSource2: MatTableDataSource<EvaluationTableRow>;

  dataSource3: MatTableDataSource<EvaluationTableRow2>;

  constructor() {
  }

  searching: FormControl = new FormControl();

  filter = 'Show All';

  ngOnInit() {
    // back end service call to populate view model
    this.viewModel2.tableRows = [{
      col1: 'hello',
      col2: new Date('2019-08-08').getMonth() + 1,
      col3: new Date('2019-08-09'),
      col4: '1623',
      col5: 'confi'
    }];
    this.dataSource2 = new MatTableDataSource(this.viewModel2.tableRows);
  }

  filterRows(): void {
    const str = this.searching.value;
    let tempArr = [];
    this.viewModel2.tableRows.filter((r) => {
      // check if r.startDate is between the start and end date
      if (!str) {
        tempArr = this.viewModel2.tableRows;
      } else if (r.col1.indexOf(str) > -1) {
        tempArr.push(r);
      }
    });
    this.dataSource2 = new MatTableDataSource(tempArr);
  }

  showAll() {
    this.dataSource2 = new MatTableDataSource(this.viewModel2.tableRows);
  }
}
