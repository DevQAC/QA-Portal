import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-cohort-chart',
  templateUrl: './cohort-chart.component.html',
  styleUrls: ['./cohort-chart.component.css']
})
export class CohortChartComponent implements OnInit {
  public chartOptions = {
    scaleShowVerticalLines: false,
    responsive: true
  };

  public chrtLabels = ['week1', 'week2', 'week3', 'week4', 'week5', 'week6', 'week7', 'week8', 'week9'];
  public chartType = 'line';
  public chartLegend = true;

  public barChartData = [
    {data: [8.5, 6.1], label: 'Name 1'},
    {data: [7.3, 4.6], label: 'Name 2'},
    {data: [9.4, 9.6], label: 'Name 3'},
    {data: [7.9, 5.9], label: 'Name 4'},
    {data: [2.3, 3.5], label: 'Name 5'}
  ];
  constructor() { }

  ngOnInit() {
  }

}
