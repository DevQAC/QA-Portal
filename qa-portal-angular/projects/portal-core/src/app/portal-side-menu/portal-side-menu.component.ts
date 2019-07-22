import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-portal-side-menu',
  templateUrl: './portal-side-menu.component.html',
  styleUrls: ['./portal-side-menu.component.css']
})
export class PortalSideMenuComponent implements OnInit {

  openedDrawer = true;

  constructor() { }

  ngOnInit() {
  }

  setOpened(value: boolean) {
    this.openedDrawer = value;
  }
}
