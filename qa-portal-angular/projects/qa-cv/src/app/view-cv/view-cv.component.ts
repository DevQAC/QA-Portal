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
    // Fetch data from API


    this.cvData = <ICvModel>{
      full_name: 'Ian Owen',
      profile: {
        p_detail: 'test p_detail',
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      },
      hobbies: {
        h_detail: "test h_detail",
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      },
      qualifications: [{
        q_detail: "qual test1",
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      },{
        q_detail: "qual test2",
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      },{
        q_detail: "qual test3",
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

  onSave(): void {
    debugger;
  }

}
