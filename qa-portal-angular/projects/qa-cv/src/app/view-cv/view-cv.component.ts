import { Component, OnInit } from '@angular/core';
import { ICvModel } from '../_common/models/qac-cv-db.model';

@Component({
  selector: 'app-view-cv',
  templateUrl: './view-cv.component.html',
  styleUrls: ['./view-cv.component.css']
})
export class ViewCvComponent implements OnInit {

  public cvData: ICvModel;
  constructor() { }

  ngOnInit() {

    this.cvData = <ICvModel>{
      full_name: "Sterling Archer",
      work_experience: [{
        job: "hacker",
        start_date: "1970-01-01",
        end_date: "1970-01-02",
        detail: "none",
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      }]
    }
  }

}
