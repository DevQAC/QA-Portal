import { Component, OnInit, Output, EventEmitter, Input } from '@angular/core';
import { SearchBarBackdropComponent } from '../search-bar-backdrop/search-bar-backdrop.component';
import { Overlay } from '@angular/cdk/overlay';

@Component({
  selector: 'app-search-bar',
  templateUrl: './search-bar.component.html'
})
export class SearchBarComponent implements OnInit {

  /**
   * Reference to a SearchBarBackdropComponent element. This element presents a gray background that can be clicked to close the filter.
   * Position one of these elements wherever makes sense and pass a reference for it here.
   */
  @Input() backdropRef: SearchBarBackdropComponent;

  /** Should the filter panel be accessable? Defaults to true. */
  @Input() public hasFilterPanel = true;

  /** Search input field placeholder text. */
  @Input() public placeholder = 'Search';

  /** Internal value for the search input. use value get/set. */
  // tslint:disable-next-line: variable-name
  private _value = '';

  /** Current value of the search input. */
  @Input() public get value(): string {
    return this._value;
  }
  public set value(val: string) {
    this._value = val;
    this.valueChange.emit(val);
  }

  /**
   * Event triggered whener the search input value changes.
   * @emits string Current value fo the search input.
   */
  @Output() public valueChange = new EventEmitter<string>();

  /**
   * Event triggered whenever a search is triggered by the user.
   * @emits string Current value of the search input.
   */
  @Output() public search = new EventEmitter<string>();

  /**
   * Event triggered whenever the open state of the filter panel changes.
   * @emits boolean Current open state of the filter panel.
   */
  @Output() public filterOpenChange = new EventEmitter<boolean>();

  /**
   * Event triggered whenever the filter apply button is clicked.
   * @emits string Current value of the search input.
   */
  @Output() public filterApply = new EventEmitter<string>();

  /**
   * Event triggered whenever the filter reset button is clicked.
   * @emits string Current value of the search input.
   */
  @Output() public filterReset = new EventEmitter<string>();

  /** Internal value for filterOpen. Use filterOpen! */
  // tslint:disable-next-line: variable-name
  private _filterOpen = false;

  /** Open state of the filter panel. */
  public get filterOpen(): boolean {
    return this._filterOpen;
  }
  public set filterOpen(open: boolean) {
    this._filterOpen = open;
    if (this.backdropRef) {
      this.backdropRef.show = open;
    }
    this.filterOpenChange.emit(open);
  }

  constructor(private overlay: Overlay) { }


  ngOnInit() {
    if (this.backdropRef) {
      this.backdropRef.onBackdropClick = () => this.filterOpen = false;
    }
  }

  public announceSearch() {
    this.filterOpen = false;
    this.search.emit(this.value);
  }

  public announceFilterApply() {
    this.filterOpen = false;
    this.filterApply.emit(this.value);
  }

  public announceFilterReset() {
    this.filterReset.emit(this.value);
  }
}
