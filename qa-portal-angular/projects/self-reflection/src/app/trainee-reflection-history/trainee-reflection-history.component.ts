import { Component, OnInit } from '@angular/core';



export interface reflection {
  id: number;
  content: string;
  date: string;
}

const reflections: reflection[] = [
  {id:1, content:'Self Reflection Form', date: '19/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'}
]

@Component({
  selector: 'app-trainee-reflection-history',
  templateUrl: './trainee-reflection-history.component.html',
  styleUrls: ['./trainee-reflection-history.component.css']
})
export class TraineeReflectionHistoryComponent implements OnInit {
  displayedColumns: string[] = ['content', 'date'];
  dataSource = reflections;

  constructor() {
    
  }

  ngOnInit() {
    
  }

}
