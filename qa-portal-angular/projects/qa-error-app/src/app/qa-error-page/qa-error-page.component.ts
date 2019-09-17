import {Component} from '@angular/core';
import {ActivatedRoute} from '@angular/router';

const ERROR_MSG_PARM = 'errorMsg';

@Component({
  selector: 'app-qa-error-page',
  templateUrl: './qa-error-page.component.html',
  styleUrls: ['./qa-error-page.component.css']
})
export class QaErrorPageComponent {

  error: string;

  constructor(private route: ActivatedRoute) {
    this.error = this.route.snapshot.queryParams[ERROR_MSG_PARM];
  }
}
