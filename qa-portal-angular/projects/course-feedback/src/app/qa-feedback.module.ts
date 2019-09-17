import {NgModule} from '@angular/core';
import {QaFeedbackRoutingModule} from './qa-feedback-routing.module';
import {QaCommonModule} from '../../../qa-common/src/app/qa-common.module';
import {TrainerEvaluationSummaryComponent} from './trainer-evaluation-summary/trainer-evaluation-summary.component';
import {TableComponentComponent} from './trainer-evaluation-summary/table-component/table-component.component';
import {CourseInfoComponent} from './trainer-evaluation-summary/course-info/course-info.component';
import {CourseEvaluationComponent} from './course-evaluation/course-evaluation.component';
import {EvaluationTableComponent} from './evaluation-table/evaluation-table.component';
import {TrainerEvaluationHistoryComponent} from './trainer-evaluation-history/trainer-evaluation-history.component';
import {TrainerCourseHistoryService} from './_common/services/trainer-course-history.service';
import {FormTypeService} from './_common/services/form-type.service';
import {TraineeCourseEvaluationComponent} from './trainee-course-evaluation/trainee-course-evaluation.component';
import {TrainerFeedbackPageComponent} from './trainer-feedback-page/trainer-feedback-page.component';
import {QaFormsModule} from 'projects/qa-forms';
import {EvaluationService} from './_common/services/evaluation-service';
import {TraineeEvaluationSummaryComponent} from './trainee-evaluation-summary/trainee-evaluation-summary.component';
import {TraineeEvaluationSummaryService} from './trainee-evaluation-summary/services/trainee-evaluation-summary.service';
import {FeedbackService} from './trainer-feedback-page/_common/services/feedback.service';
import { TrainerFeedbackHistoryComponent } from './trainer-feedback-history/trainer-feedback-history.component';
import {RetrieveTrainerEvaluationHistoryService} from './trainer-evaluation-history/services/retrieve-trainer-evaluation-history.service';
import {TrainerEvaluationService} from './trainer-evaluation-summary/services/trainer-evaluation.service';


@NgModule({
  declarations: [
    TrainerEvaluationSummaryComponent,
    TableComponentComponent,
    CourseInfoComponent,
    CourseEvaluationComponent,
    EvaluationTableComponent,
    TrainerEvaluationHistoryComponent,
    TraineeCourseEvaluationComponent,
    TrainerFeedbackPageComponent,
    TraineeEvaluationSummaryComponent,
    TrainerFeedbackHistoryComponent
  ],
  imports: [
    QaCommonModule,
    QaFeedbackRoutingModule,
    QaFormsModule
  ],
  providers: [
    TrainerCourseHistoryService,
    TraineeEvaluationSummaryService,
    RetrieveTrainerEvaluationHistoryService,
    FormTypeService,
    FeedbackService,
    EvaluationService,
    TrainerEvaluationService
  ]
})
export class QaFeedbackModule { }
