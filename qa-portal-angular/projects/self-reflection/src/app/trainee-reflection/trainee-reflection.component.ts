import {Component, OnDestroy, OnInit} from '@angular/core';
import {QuestionsServiceService} from './services/questions-service.service';
import {SelfReflectionFormViewModel} from './models/self-reflection-form-vmodel';
import {RatedQuestionsService} from './services/rated-questions.service';
import {SelfReflectionFormService} from './services/self-reflection-form.service';
import {Subscription} from 'rxjs';

@Component({
  selector: 'app-trainee-reflection',
  templateUrl: './trainee-reflection.component.html',
  styleUrls: ['./trainee-reflection.component.css']
})
export class TraineeReflectionComponent implements OnInit, OnDestroy {

  selfReflectionViewModel = new SelfReflectionFormViewModel();
  questionsArray: any[] = [];
  questionSubscription: Subscription;
  title = 'hello';

  constructor(private ratedQuestionsService: RatedQuestionsService,
              private selfReflectionFormService: SelfReflectionFormService,
              private questionsService: QuestionsServiceService) {
  }

  ngOnInit() {
    this.questionSubscription = this.questionsService.getQuestions().subscribe((response) => {
      this.questionsArray = response;
      console.log(response);
    });
  }

  ngOnDestroy(): void {
    this.questionSubscription.unsubscribe();
  }
}
