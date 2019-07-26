import { Component, OnInit, ViewChild } from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {ReflectionHistoryService} from './services/reflection-history.service';
import {reflection} from './models/reflections';

@Component({
  selector: 'app-self-reflection-history',
  templateUrl: './self-reflection-history.component.html',
  styleUrls: ['./self-reflection-history.component.css']
})
export class SelfReflectionHistoryComponent implements OnInit {
  newReflections: reflection[];
  displayedColumns: string[] = ['content', 'date'];
  dataSource = new MatTableDataSource<reflection>(this.newReflections);
  today = new Date().getDay();
  upcomingFriday = new Date();

  @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;

  constructor(private reflectionHistoryService: ReflectionHistoryService) {  }

  ngOnInit() {
    this.newReflections = this.reflectionHistoryService.getTraineeReflections();
    this.dataSource = new MatTableDataSource<reflection>(this.newReflections);
    this.dataSource.paginator = this.paginator;
  }

}
