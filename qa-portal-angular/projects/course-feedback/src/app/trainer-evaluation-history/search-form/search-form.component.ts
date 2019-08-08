import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { TrainerEvaluationHistoryComponent } from '../trainer-evaluation-history.component';

@Component({
  selector: 'app-search-form',
  templateUrl: './search-form.component.html',
  styleUrls: ['./search-form.component.css']
})
export class SearchFormComponent implements OnInit {

  searchForm: FormControl = new FormControl();

  constructor() { }

  ngOnInit() {
  }
  // private eval: TrainerEvaluationHistoryComponent
}
