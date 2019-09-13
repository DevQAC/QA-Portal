import { Component, Input, Output, EventEmitter } from '@angular/core';
import { IProfile } from '../_common/models/profile.model';
import { FormGroup, FormControl, FormBuilder } from '@angular/forms';


@Component({
  selector: 'app-cv-profile',
  templateUrl: './cv-profile.component.html',
  styleUrls: ['./cv-profile.component.css']
})
export class CvProfileComponent {
  @Input() profile: IProfile;
  @Output() profileChange = new EventEmitter<IProfile>();
  @Input() canEdit: boolean;

  onInputChange(data: string): void {
    this.profile.profileDetails = data;
    this.profileChange.emit(this.profile);
  }

  isDisabled() {
    return !this.canEdit;
  }

}
