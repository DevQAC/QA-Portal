import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IQualification } from '../../_common/models/qualification.model';

@Component({
  selector: 'app-cv-qualis',
  templateUrl: './cv-qualis.component.html',
  styleUrls: ['./cv-qualis.component.css']
})
export class CvQualisComponent implements OnInit {
  @Input() qualifications: IQualification;
  @Output() qualificationsChange = new EventEmitter<IQualification>();

  constructor() { }

  ngOnInit() {
  }

  onInputChange(data) {
    this.qualifications.q_detail = data;
    this.qualificationsChange.emit(this.qualifications);
  }

}
