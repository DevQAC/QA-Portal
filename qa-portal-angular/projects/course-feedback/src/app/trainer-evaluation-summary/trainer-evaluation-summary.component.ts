import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, ParamMap} from '@angular/router';
import {TrainerEvaluationService} from './services/trainer-evaluation.service';
import {TraineeEvaluation} from './models/trainee-evaluation-data';
import {TrainerEvaluationSummaryModel} from './models/trainer-evaluation-summary.model';

@Component({
  selector: 'app-trainer-evaluation-summary',
  templateUrl: './trainer-evaluation-summary.component.html',
  styleUrls: ['./trainer-evaluation-summary.component.css']
})
export class TrainerEvaluationSummaryComponent implements OnInit {
   viewModel: TrainerEvaluationSummaryModel;

  dataSource: TraineeEvaluation[] = [];
  courseHistoryTqi: string;
  tqi: string;

  // course info data
  courseName: string;
  location: string;
  startDate: string;
  duration: number;

  constructor(private trainerEvaluationService: TrainerEvaluationService,
              private activatedRoute: ActivatedRoute) {
  }

  ngOnInit() {
    this.initalizeTable();
  }

  initalizeTable(): void {
    this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        const courseId = params.get('id');
        this.trainerEvaluationService.getTrainerCourseEvaluationSummary(courseId).subscribe(response => {
          this.viewModel = response;
          this.tqi = response.courseTqi;
          this.courseHistoryTqi = response.courseHistoryTqi;
          this.getCourseInfo(response.traineeEvaluationsForCourse[0]);
          this.getTraineeEvaulations(response.traineeEvaluationsForCourse);
        });
      });
  }

  getCourseInfo(data) {
    if (data) {
      const cohortCourse = data.cohortCourse;

      this.courseName = cohortCourse.course.courseName;
      this.location = cohortCourse.location.name;

      const end = new Date(cohortCourse.endDate);
      const start = new Date(cohortCourse.startDate);
      const diffTime = Math.abs(end.getTime() - start.getTime());

      this.startDate = start.getDate() + '/' + start.getMonth() + '/' + start.getFullYear();
      this.duration = Math.ceil(diffTime / (1000 * 60 * 60 * 24)) + 1;
    }
  }

  getTraineeEvaulations(data): void {
    const tempData = [];
    for (const response of data) {
      const evaluation: TraineeEvaluation = {
        traineeName: response.trainee.firstName + ' ' + response.trainee.lastName,
        knowledge: '',
        recommend: ''
      };
      for (const catResponse of response.categoryResponses) {
        if (catResponse.questionCategory.categoryName === 'Evaluation Trainer') {
          evaluation.knowledge = catResponse.questionResponses[0].responseValues[0];
        } else if (catResponse.questionCategory.categoryName === 'Evaluation Recommend QA') {
          evaluation.recommend = catResponse.questionResponses[0].responseValues[0];
        }
      }
      tempData.push(evaluation);
    }
    this.dataSource = tempData;
  }
}
