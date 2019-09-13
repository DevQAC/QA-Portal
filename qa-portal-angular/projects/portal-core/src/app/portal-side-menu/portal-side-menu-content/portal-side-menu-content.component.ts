import { Component, EventEmitter, Input, OnDestroy, OnInit, Output } from '@angular/core';
import { ApplicationSelectionService } from '../../_common/services/application-selection.service';
import { Subscription, Observable } from 'rxjs';
import { PortalProjectModel } from '../../_common/models/portal-project.model';
import { PortalApplicationProjectsModel } from '../../_common/models/portal-application-projects.model';
import { SideMenuService } from '../../_common/services/side-menu.service';

@Component({
  selector: 'app-portal-side-menu-content',
  templateUrl: './portal-side-menu-content.component.html',
  styleUrls: ['./portal-side-menu-content.component.css']
})
export class PortalSideMenuContentComponent implements OnInit, OnDestroy {

  private projectSelectionSubscription: Subscription;

  public portalApplicationProjectsObservable$: Observable<PortalApplicationProjectsModel>;

  selectedProject: PortalProjectModel = new PortalProjectModel();

  @Output() openedDrawerEmmiter = new EventEmitter();

  @Input() opened: boolean;

  constructor(
    private applicationSelectionService: ApplicationSelectionService,
    public sideMenuService: SideMenuService) { }

  ngOnInit() {
    this.projectSelectionSubscription
      = this.applicationSelectionService.getSelectedProject$()
        .subscribe(proj => {
          this.selectedProject = proj;
          console.log('Side menu subscription project has following number of pages ' + this.selectedProject.projectPages.length);
        });
    this.portalApplicationProjectsObservable$ = this.applicationSelectionService.getSelectedApplication$();
  }

  ngOnDestroy(): void {
    this.projectSelectionSubscription.unsubscribe();
  }

  toggleDrawer() {
    this.opened = !this.opened;
    this.openedDrawerEmmiter.emit(this.opened);
  }

  errorApp(): boolean {
    // TODO - remove hard coding
    return this.selectedProject.url === '/qa/portal/error';
  }
}
