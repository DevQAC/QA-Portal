import { Component, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup } from '@angular/forms';
import { TrainerEvaluationHistoryComponent } from '../trainer-evaluation-history.component';
import { filter } from 'minimatch';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  @Output() searchForm = new FormGroup({
    search: new FormControl('')
  });
  

  constructor(private use: TrainerEvaluationHistoryComponent) { }

  ngOnInit() {
  }
  // private eval: TrainerEvaluationHistoryComponent

  filter() {
    this.use.filterRows();
  }

}


