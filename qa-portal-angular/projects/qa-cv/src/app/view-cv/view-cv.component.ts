import { Component, OnInit } from '@angular/core';
import { ICvModel } from '../_common/models/qac-cv-db.model';
import { ViewCvService } from '../_common/services/view-cv-service.service';

@Component({
  selector: 'app-view-cv',
  templateUrl: './view-cv.component.html',
  styleUrls: ['./view-cv.component.css']
})
export class ViewCvComponent implements OnInit {
  cvs: ICvModel[] = [];

  public cvData: ICvModel;

  constructor(
    private cvService: ViewCvService
  ) { }

  ngOnInit() {
    this.getAllCvs();
  }

  onSave(): void {
    debugger;
  }

  getAllCvs(): void {
    this.cvData = this.cvService.getAllCvs()
    //.subscribe(cvs => this.cvs = cvs);
  }

}
