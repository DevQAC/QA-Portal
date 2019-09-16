import { Component, EventEmitter, Input, Output } from '@angular/core';
import { IHobbies } from '../_common/models/hobbies.model';

@Component({
  selector: 'app-cv-hobbies',
  templateUrl: './cv-hobbies.component.html',
  styleUrls: ['./cv-hobbies.component.css']
})
export class CvHobbiesComponent {
  @Input() hobbies: IHobbies;
  @Output() hobbiesChange = new EventEmitter<IHobbies>();
  @Input() canEdit: boolean;

  onInputChange(data: string): void {
    this.hobbies.hobbiesDetails = data;
    this.hobbiesChange.emit(this.hobbies);
  }

  isDisabled() {
    return !this.canEdit;
  }


}
