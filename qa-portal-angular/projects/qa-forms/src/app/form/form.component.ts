import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { ICategory } from '../_common/models/form-category.model';

@Component({
  selector: 'app-qa-form',
  templateUrl: './form.component.html',
  styleUrls: ['./form.component.css']
})
export class FormComponent implements OnInit {

  @Input() model: ICategory[];
  @Output() change = new EventEmitter<ICategory[]>();

  ngOnInit() {
  }

}
