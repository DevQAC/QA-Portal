import { Component, OnInit } from '@angular/core';



@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})



export class CourseInfoComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }

  courseName = "API";
  location = "Manchester";
  startDate = "07/08/2019";
  duration = 5;

}
