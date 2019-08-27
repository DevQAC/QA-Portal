import { Component, OnInit, EventEmitter, Input, Output } from '@angular/core';
import { IHobbies } from '../_common/models/hobbies.model';

@Component({
  selector: 'app-cv-hobbies',
  templateUrl: './cv-hobbies.component.html',
  styleUrls: ['./cv-hobbies.component.css']
})
export class CvHobbiesComponent implements OnInit {
  @Input() hobbies: IHobbies;
  @Output() hobbiesChange = new EventEmitter<IHobbies>();


  constructor() { }

  ngOnInit() {
  }

  onInputChange(data) {
    this.hobbies.h_detail = data;
    this.hobbiesChange.emit(this.hobbies);
  }


}
