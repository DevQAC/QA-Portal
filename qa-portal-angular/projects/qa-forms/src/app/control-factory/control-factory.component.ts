import { Component, OnInit, Input } from '@angular/core';
import { IGenericQuestion } from '../_common/models/generic-question.model';

@Component({
  selector: 'app-control-factory',
  templateUrl: './control-factory.component.html',
  styleUrls: ['./control-factory.component.css']
})
export class ControlFactoryComponent implements OnInit {

  @Input() question: IGenericQuestion<any>;

  constructor() { }

  ngOnInit() {
  }

}
