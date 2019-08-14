import { Component, OnInit } from '@angular/core';
import { COURSE_EVAL_FORM } from '../../_common/models/question_url_constants';
import { FormTypeService } from '../../_common/services/form-type.service';
import { DataModel } from '../../_common/models/data.model';
import { ActivatedRoute, ParamMap } from '@angular/router';
import { SelfReflectionService } from 'projects/self-reflection/src/app/trainer-reflection/services/self-reflection.service';
import { TraineeModel } from 'projects/self-reflection/src/app/trainer-reflection/models/dto/trainee.model';
import { UserResponseModel } from '../../_common/models/user-response';

@Component({
  selector: 'app-feedback-page',
  templateUrl: './feedback-page.component.html',
  styleUrls: ['./feedback-page.component.css']
})
export class FeedbackPageComponent implements OnInit {

  public formInfo: DataModel[] = [];
  public trainee: TraineeModel = new TraineeModel();
  public userResponse: UserResponseModel = new UserResponseModel();

  constructor(
    private formTypeService: FormTypeService,
    private activatedRoute: ActivatedRoute,
    private reflectionService: SelfReflectionService
  ) { }

    ngOnInit() {
      this.formTypeService.getFormType(COURSE_EVAL_FORM).subscribe((response: DataModel[]) => {
        this.formInfo = response;
        this.activatedRoute.paramMap.subscribe((pm: ParamMap): void => {
          const traineeId = +pm.get('id');
          this.reflectionService.getTraineeById(traineeId).subscribe((trainee: TraineeModel): void => {
            this.trainee = trainee;
      });
      });
      });
    }
}
