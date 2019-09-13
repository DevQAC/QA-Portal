import { Component, HostListener } from '@angular/core';

@Component({
  selector: 'app-search-bar-backdrop',
  template: `
    <div class="search-bar-backdrop" [class.visible]="show"></div>
  `
})
export class SearchBarBackdropComponent {
  public show = false;
  public onBackdropClick = () => { };

  @HostListener('click') _handleClick() {
    this.show = false;
    this.onBackdropClick();
  }

}
