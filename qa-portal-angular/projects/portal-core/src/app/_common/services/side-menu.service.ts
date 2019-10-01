import { Injectable } from '@angular/core';

/**
 * Service to control and persist side menu specific functionality.
 *
 * @export
 */
@Injectable({
  providedIn: 'root'
})
export class SideMenuService {

   /* Side menu open state. Use toggleOpen / setOpenState to persist the state across sessions. */
  public sideMenuOpen = false;

  constructor() {
    const persistedOpenString = localStorage.getItem('sideMenuOpen');
    this.sideMenuOpen = persistedOpenString !== undefined ? (persistedOpenString === 'true') : this.sideMenuOpen;
  }

  /**
   * Toggles the side menu open state.
   * @returns returns the new state of the side menu
   */
  public toggleOpen(): boolean {
    return this.setOpenState(!this.sideMenuOpen);
  }

  /**
   * Sets the side menu open state.
   * @returns returns the new state of the side menuP
   */
  public setOpenState(open: boolean): boolean {
    this.sideMenuOpen = open;
    localStorage.setItem('sideMenuOpen', this.sideMenuOpen.toString());
    return this.sideMenuOpen;
  }
}
