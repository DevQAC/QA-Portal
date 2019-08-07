import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-trainee-course-evaluation',
  templateUrl: './trainee-course-evaluation.component.html',
  styleUrls: ['./trainee-course-evaluation.component.css']
})
export class TraineeCourseEvaluationComponent implements OnInit {

  constructor() { }

  ngOnInit() {
  }
  submitForm() {
    this.TraineeCourseEvaluationComponent.(this.selfReflectionViewModel.selfReflectionForm).subscribe(
      (response) => {
        this.router.navigateByUrl('qa/portal/training/trainee/selfreflections');
      },
      (error) => {
        this.errorHandlerService.handleError(error);
      }
    );
  }

}
