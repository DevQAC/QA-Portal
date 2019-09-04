import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { TrainerEvaluationServicesService } from './services/trainer-evaluation-services.service';
import { TraineeEvaluation } from './models/trainee-evaluation-data';

@Component({
  selector: 'app-trainer-evaluation-summary',
  templateUrl: './trainer-evaluation-summary.component.html',
  styleUrls: ['./trainer-evaluation-summary.component.css']
})
export class TrainerEvaluationSummaryComponent implements OnInit {
  //table data
  dataSource: TraineeEvaluation[] = [];
  average: number = 0;
  tqi: number = 0;

  //course info data
  courseName: string;
  location: string;
  startDate: string;
  duration: number;

  constructor(private trainerEvaluationService: TrainerEvaluationServicesService,
               private activatedRoute: ActivatedRoute) { }

  ngOnInit() {
    this.initalizeTable();
  }

  initalizeTable() : void {
    this.activatedRoute.paramMap.subscribe(
      (params: ParamMap) => {
        const courseId = params.get('id');
        this.trainerEvaluationService.GetTrainerFeedback(courseId).subscribe(data => {
          this.getCourseInfo(data[0]);
          this.getTraineeEvaulations(data);
        });
      });
  }

  getCourseInfo(data){
    if(data){
      let cohortCourse = data.cohortCourse;
      this.average = parseInt(cohortCourse.average);
      this.tqi = parseInt(cohortCourse.tqi);
    
      this.courseName = cohortCourse.course.courseName;
      this.location = "???";
      
      const end = new Date(cohortCourse.endDate);
      const start = new Date(cohortCourse.startDate);
      const diffTime = Math.abs(end.getTime() - start.getTime());

      this.startDate = start.getDate() + "/" + start.getMonth() + "/" + start.getFullYear();
      this.duration = Math.ceil(diffTime / (1000 * 60 * 60 * 24));
    }
  }

  getTraineeEvaulations(data) : void {
    let tempData = [];
    for(let response of data) {
      let evaluation: TraineeEvaluation = {traineeName: response.trainee.firstName + " " + response.trainee.lastName, knowledge: "", recommend: ""};
      for(let catResponse of response.categoryResponses) {
        if(catResponse.questionCategory.categoryName === "Evaluation Trainer") {
          evaluation.knowledge = catResponse.questionResponses[0].responseValues[0];
        } else if(catResponse.questionCategory.categoryName === "Evaluation Recommend QA") {
          evaluation.recommend = catResponse.questionResponses[0].responseValues[0];
        }
      } 
      //this.dataSource.push(evaluation) doesn't work for... reasons
      tempData.push(evaluation);
    }
    this.dataSource = tempData;
  }
}