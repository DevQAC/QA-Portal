import { Component, OnInit } from '@angular/core';
import { enableDebugTools } from '@angular/platform-browser';
import { templateJitUrl } from '@angular/compiler';

@Component({
  selector: 'app-course-evaluation',
  templateUrl: './course-evaluation.component.html',
  styleUrls: ['./course-evaluation.component.css']
})
export class CourseEvaluationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
    DisplayName();

    function DisplayName(){
      var name = document.getElementById('user-name');
      name.innerHTML = "Allan Charles";
    }
  }
}
