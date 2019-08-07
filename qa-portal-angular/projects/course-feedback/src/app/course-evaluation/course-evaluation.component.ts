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
    PopulateTable();
    DisplayName();


    function DisplayName(){
      var name = document.getElementById('user-name');
      name.innerHTML = "Allan Smels";
    }

    function PopulateTable() {
      //Replace with content from database
      var numberOfRows = 2; 
      var numberOfColumns = 6;
  
      var table = <HTMLTableElement>document.getElementById('evaluation-table');
  
      for(var i = 0; i < numberOfRows; i++){
        var row = table.insertRow();
        for(var j = 0; j < numberOfColumns; j++){
          var k = j;
          
          var cell = row.insertCell(j);
          cell.setAttribute('id', 'testTd');
          cell.innerHTML = "Row: " + i + " Item: " + k;
        }
      }
    }
  }
}
