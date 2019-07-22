import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {ApplicationSelectionService} from '../../_common/services/application-selection.service';
import {Subscription} from 'rxjs';
import {Application} from '../../_common/models/application';

@Component({
  selector: 'app-portal-side-menu-content',
  templateUrl: './portal-side-menu-content.component.html',
  styleUrls: ['./portal-side-menu-content.component.css']
})
export class PortalSideMenuContentComponent implements OnInit, OnDestroy {

  private applicationSelectionSubscription: Subscription;

  selectedApplication: Application = new Application();

  @Output() openedDrawerEmmiter = new EventEmitter();

  @Input() opened: boolean;

  constructor(private applicationSelectionService: ApplicationSelectionService) { }

  ngOnInit() {
    this.applicationSelectionSubscription
      = this.applicationSelectionService.getSelectedApplication$()
                                                  .subscribe(app => {
                                                    this.selectedApplication = app;
                                                  });
  }

  ngOnDestroy(): void {
    this.applicationSelectionSubscription.unsubscribe();
  }

  toggleDrawer() {
    this.opened = !this.opened;
    this.openedDrawerEmmiter.emit(this.opened);
  }

  errorApp(): boolean {
    // TODO - remove hard coding
    return this.selectedApplication.url === '/qa/portal/error';
  }
}
