import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IQualification } from '../../_common/models/qualification.model';


@Component({
  selector: 'app-cv-qualis',
  templateUrl: './cv-qualis.component.html',
  styleUrls: ['./cv-qualis.component.css']
})
export class CvQualisComponent implements OnInit {
  @Input() qualifications1: IQualification;
  @Output() qualificationsChange = new EventEmitter<IQualification>();

  @Output() deleteQualification = new EventEmitter<IQualification>();

  
 

  constructor() { }

  
  deleteQuali(): void {

    this.deleteQualification.emit(this.qualifications1);

  }

  ngOnInit() {
  }

  onInputChange(data) {
    this.qualifications1.qualificationDetails = data;
    this.qualificationsChange.emit(this.qualifications1);
  }

}
