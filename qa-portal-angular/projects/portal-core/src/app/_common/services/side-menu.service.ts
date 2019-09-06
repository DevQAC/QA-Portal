import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SideMenuService {

  public sideMenuOpen = true;

  public toggleOpen(): boolean {
    this.sideMenuOpen = !this.sideMenuOpen;
    return this.sideMenuOpen;
  }
}