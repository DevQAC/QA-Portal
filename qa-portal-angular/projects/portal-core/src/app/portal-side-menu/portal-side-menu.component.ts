import { Component } from '@angular/core';
import {
  trigger,
  state,
  style,
  animate,
  transition
} from '@angular/animations';
import { SideMenuService } from '../_common/services/side-menu.service';

@Component({
  selector: 'app-portal-side-menu',
  templateUrl: './portal-side-menu.component.html',
  animations: [
    trigger('sidenavAnimationIsExpanded', [
      state('true', style({
        width: '300px'
      })),
      state('false', style({
        width: '56px'
      })),
      transition('false <=> true', animate('100ms ease-in-out'))
    ])
  ]
})
export class PortalSideMenuComponent {
  constructor(public sideMenuService: SideMenuService) { }

  animating = false;

  start() {
    this.animating = true;
    this.tick();
  }

  done() {
    this.animating = false;
  }

  tick() {
    if (this.animating) { requestAnimationFrame(() => this.tick()); }
  }
}
