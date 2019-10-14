import { Component, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-new-form-dialog',
  templateUrl: './new-form-dialog.component.html',
  styleUrls: ['./new-form-dialog.component.css']
})
export class NewFormDialogComponent implements OnInit {

  public formForm: FormGroup;
  public isLoading = true;

  constructor() { }

  ngOnInit() {
  }

}
