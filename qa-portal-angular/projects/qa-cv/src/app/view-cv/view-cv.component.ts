import { Component, OnInit } from '@angular/core';
import { ICvModel } from '../_common/models/qac-cv-db.model';
import { ViewCvService } from '../_common/services/view-cv.service';
import { CvCardBaseComponent } from '../cv-card-base/cv-card-base.component';
import { IFeedback } from '../_common/models/feedback.model';

@Component({
  selector: 'app-view-cv',
  templateUrl: './view-cv.component.html',
  styleUrls: ['./view-cv.component.scss']
})
export class ViewCvComponent implements OnInit {
  cvs: ICvModel[] = [];
  openThis = false;

  public cvData: ICvModel;

  public workExpFeedback = [];
  workExpFeedbackIndex: number;
  public workExpDrawerOpen = false;

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

  onWorkExpFeedbackClick({index}: {index: number}, cardBase: CvCardBaseComponent): void {
    this.workExpFeedbackIndex = index;
    this.workExpFeedback = this.cvData.allWorkExperience[index].workExperienceFeedback;
    cardBase.drawer.open();
  }

  onWorkExpFeedbackChange(feedback: IFeedback[], ): void {
    this.cvData.allWorkExperience[this.workExpFeedbackIndex].workExperienceFeedback = feedback;
  }

}
