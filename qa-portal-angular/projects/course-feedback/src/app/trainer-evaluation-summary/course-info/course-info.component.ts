import { Component, OnInit, Input } from '@angular/core';

@Component({
  selector: 'app-course-info',
  templateUrl: './course-info.component.html',
  styleUrls: ['./course-info.component.css']
})
export class CourseInfoComponent implements OnInit {
  @Input() courseName;
  @Input() location;
  @Input() startDate;
  @Input() duration;
  @Input() courseId;

  constructor() { }

  ngOnInit() {
  }
}
