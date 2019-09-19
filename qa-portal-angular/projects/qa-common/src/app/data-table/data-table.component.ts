import { Component, Input, ViewChild, AfterContentInit, ChangeDetectorRef, ContentChildren, EventEmitter, Output } from '@angular/core';
import { MatTableDataSource, MatColumnDef, MatTable, MatCheckboxChange } from '@angular/material';
import { IRowClickEvent } from './models/row-click-event';
import * as _ from 'lodash';

/**
 * A common interactive table for displaying and interacting with data.
 * This component is build on MatTable with additional QA-Portal features added.
 *
 * @example
 * // example.component.html
 * // Adding data table to your component template
 * <app-data-table [displayedColumns]="columns" [dataSource]="tableDataSource">
 *    <ng-container matColumnDef="nameColumn">
 *        <th mat-header-cell *matHeaderCellDef>Name</th>
 *        <td mat-cell *matCellDef="let row">{{row}}</td>
 *    </ng-container>
 * </app-data-table>
 *
 * // example.component.ts
 * // Providing a dataSource. This is a standard MatTableDataSource object as provided by Angular Material.
 * public tableDataSource = new MatTableDataSource<string>(['Ian', 'Scott']);
 * // Set which columns to display with an array of column names.
 * public columns = ['nameColumn'];
 *
 * @export
 * @template DataType Type of data being provied via 'dataSource'
 */
@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html'
})
export class DataTableComponent<DataType> implements AfterContentInit {
  /** Base MatTable instance */
  @ViewChild('table', { static: false }) public matTable: MatTable<DataType>;
  /** Columns being provided with ng-content */
  @ContentChildren(MatColumnDef) public columns: MatColumnDef[];

  // Inputs
  /** MatTableDataSource for the MatTable to use. This is required. */
  @Input() public dataSource: MatTableDataSource<DataType>;
  /** Which columns to display. */
  @Input() public displayedColumns: string[] = [];
  @Input() public isLoading = false;

  // Events
  @Output() rowSelectionChange = new EventEmitter<boolean[]>();
  @Output() rowClick = new EventEmitter<IRowClickEvent<DataType>>();

  // Internal props
  public rowSelection = [];
  public actualDisplayedColumns: string[] = [];
  public allRowsSelected = false;
  public atLeastOneRowSelected = false;

  public lastSelectionIndex: number = null;


  constructor(private cdRef: ChangeDetectorRef) { }

  ngAfterContentInit() {
    this.cdRef.detectChanges();
    for (const col of this.columns) {
      this.matTable.addColumnDef(col);
    }

    this.actualDisplayedColumns = this.displayedColumns;
  }

  onRowClicked(index: number, data: DataType, event: MouseEvent | KeyboardEvent): void {
    this.rowClick.emit({ index, data, event });
  }


  // ROW SELECTION
  updateRowSelectedState() {
    this.allRowsSelected = this.rowSelection.length === this.dataSource.data.length &&
      (this.rowSelection.every(x => x) || false);
    this.atLeastOneRowSelected = this.rowSelection.some(x => x);
    this.rowSelectionChange.emit(this.rowSelection);
  }

  public onSelectAllCheckboxChange({ checked }: MatCheckboxChange) {
    this.rowSelection = checked ? Array(this.dataSource.data.length).fill(true) : [];
    this.updateRowSelectedState();
  }

  public deselectAllRows(): void {
    this.rowSelection = [];
    this.updateRowSelectedState();
  }

  public onRowCheckboxClicked(event: MouseEvent, index: number) {
    event.preventDefault(); // Stop default checkbox behaviour

    if (event.shiftKey && this.lastSelectionIndex !== null) { // SHIFT click
      if (!event.ctrlKey) {
        this.rowSelection = []; // Not holding CTRL too? Clear existing slection.
      }

      this.rowSelection[index] = true; // Set clicked row to true.

      for (const i of _.range(this.lastSelectionIndex, index)) {
        this.rowSelection[i] = true; // Select all rows from last to current row.
      }
    } else { // Normal click
      this.rowSelection[index] = this.rowSelection[index] === undefined ? true : !this.rowSelection[index];
      this.lastSelectionIndex = index;
    }

    this.updateRowSelectedState();
  }

  // MISC. UTIL FUNCTIONS
  public getSelectedRowsData(): DataType[] {
    // tslint:disable-next-line: variable-name
    return this.rowSelection.map((_row, index) => this.dataSource.data[index]);
  }

}
