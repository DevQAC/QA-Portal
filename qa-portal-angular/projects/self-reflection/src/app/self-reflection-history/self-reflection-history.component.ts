import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';


export interface reflection {
  id: number;
  content: string;
  date: string;
}

const reflections: reflection[] = [
  {id:1, content:'Self Reflection Form', date: '19/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'},
  {id:1, content:'Self Reflection Form', date: '19/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'},
  {id:1, content:'Self Reflection Form', date: '19/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'},
  {id:2, content:'Self Reflection Form', date: '12/07/2019'}
]

@Component({
  selector: 'app-self-reflection-history',
  templateUrl: './self-reflection-history.component.html',
  styleUrls: ['./self-reflection-history.component.css']
})
export class SelfReflectionHistoryComponent implements OnInit {
  displayedColumns: string[] = ['content', 'date'];
  dataSource = new MatTableDataSource<reflection>(reflections);
  today = new Date().getDay();
  upcomingFriday = new Date();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor() {
    
  }

  ngOnInit() {
    this.dataSource.paginator = this.paginator;
  }

}
