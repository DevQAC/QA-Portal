import { Component, OnInit, Input } from '@angular/core';
import { DataModel } from '../../_common/models/data.model';

@Component({
  selector: 'app-stepper',
  templateUrl: './stepper.component.html',
  styleUrls: ['./stepper.component.css']
})
export class StepperComponent implements OnInit {

  @Input() value : DataModel;

  constructor() { }

  ngOnInit() {
  }

}
