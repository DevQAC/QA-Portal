import { Component } from '@angular/core';
import { ApplicationService } from './_common/services/application.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  constructor(appServ: ApplicationService) {
    appServ.onApplicationLoaded();
  }

}

