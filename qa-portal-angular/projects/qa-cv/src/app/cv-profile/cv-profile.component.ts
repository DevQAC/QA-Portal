import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { IProfile } from '../_common/models/profile.model';


@Component({
  selector: 'app-cv-profile',
  templateUrl: './cv-profile.component.html',
  styleUrls: ['./cv-profile.component.css']
})
export class CvProfileComponent {
  @Input() profile: IProfile;
  @Output() profileChange = new EventEmitter<IProfile>();

  onInputChange(data) {
    this.profile.profileDetails = data;
    this.profileChange.emit(this.profile);
  }

}
