import {Component, Input, OnChanges, OnInit} from '@angular/core';
import {Chart} from 'chart.js';
import {CohortSummaryModel} from '../../_common/models/cohort-summary.model';

@Component({
  selector: 'app-cohort-chart',
  templateUrl: './cohort-chart.component.html',
  styleUrls: ['./cohort-chart.component.css']
})
export class CohortChartComponent implements OnInit, OnChanges {
  chart;

  labels: string[] = ['Wk1', 'Wk2', 'Wk3', 'Wk4', 'Wk5', 'Wk6', 'Wk7', 'Wk8', 'Wk9', 'Wk10', 'Wk11', 'Wk12'];

  chartDataSets: any[] = [];

  @Input() cohortSummaryData: CohortSummaryModel[];

  constructor() {
  }

  ngOnInit() {
    this.chart = new Chart('cohortCanvas', {
      type: 'line',
      data: {
        labels: this.labels,
        datasets: this.chartDataSets
      },
      options: {
        responsive: true,
        // maintainAspectRatio: false,
        animation: {
          duration: 0
        },
        hover: {
          animationDuration: 0
        },
        responsiveAnimationDuration: 0,
        legend: {
          display: false
        },
        scales: {
          xAxes: [{
            display: true
          }],
          yAxes: [{
            display: true
          }],
        }
      }
    });
  }

  ngOnChanges() {
    if (!!this.cohortSummaryData) {
      this.cohortSummaryData.forEach(r => {
        this.chartDataSets.push({
          label: r.cohortName,
          data: r.averageRatings,
          fill: false,
          borderWidth: 1,
          borderColor: this.random_rgba(),
          lineTension: 0.2
        });
      });
    }

    if (!!this.chart) {
      this.chart.update();
    }
  }

  private random_rgba() {
    const o = Math.round;
    const r = Math.random;
    const s = 255;
    return 'rgba(' + o(r() * s) + ',' + o(r() * s) + ',' + o(r() * s) + ',' + r().toFixed(1) + ')';
  }
}
