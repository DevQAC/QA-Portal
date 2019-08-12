import { Component, OnInit, Input } from '@angular/core';
import { DataModel } from '../../_common/models/data.model';

@Component({
  selector: 'app-question-category',
  templateUrl: './question-category.component.html',
  styleUrls: ['./question-category.component.css']
})
export class QuestionCategoryComponent implements OnInit {


  @Input() value: DataModel;

  constructor() { }

  ngOnInit() {
    console.log(this.value);
  }

}
