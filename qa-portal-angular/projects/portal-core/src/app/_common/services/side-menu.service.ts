import { Injectable } from '@angular/core';

/**
 * Service to control and persist side menu specific functionality.
 *
 * @export
 * @class SideMenuService
 */
@Injectable({
  providedIn: 'root'
})
export class SideMenuService {

  /**
   * Side menu open state. Use toggleOpen to persist the state across sessions.
   *
   * @memberof SideMenuService
   */
  public sideMenuOpen = false;

  constructor() {
    const persistedOpenString = localStorage.getItem('sideMenuOpen');
    this.sideMenuOpen = persistedOpenString !== undefined ? (persistedOpenString === 'true') : this.sideMenuOpen;
  }

  /**
   * Toggles the side menu open state.
   *
   * @returns {boolean} returns the new state of the side menu
   * @memberof SideMenuService
   */
  public toggleOpen(): boolean {
    this.sideMenuOpen = !this.sideMenuOpen;
    localStorage.setItem('sideMenuOpen', this.sideMenuOpen.toString());
    return this.sideMenuOpen;
  }
}
