import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
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
  public form: FormGroup;

  onInputChange(data) {
    this.profile.profileDetails = data;
    this.profileChange.emit(this.profile);
  }

  constructor(private fb: FormBuilder) {}

  ngOnInit() {
    this.form = this.fb.group({
      profile: new FormControl({ value: '', disabled: this.canEdit })
    });
}

}
