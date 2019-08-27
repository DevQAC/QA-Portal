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
      full_name: "Sterling Archer",
      work_experience: [{
        job: "hacker",
        start_date: "1970-01-01",
        end_date: "1970-01-02",
        detail: "none",
      }],
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
      }, {
        q_detail: "qual test2",
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      }, {
        q_detail: "qual test3",
        feedback: [
          {
            who: 'me',
            date: '2000-01-01',
            comment: 'test comment'
          }
        ]
      }]
<<<<<<< HEAD
    }
=======

    }
  }

  onSave(): void {
    debugger;
>>>>>>> 2f240a3db10aff4bdff73eb93926ff4cfbaa998c
  }

}
