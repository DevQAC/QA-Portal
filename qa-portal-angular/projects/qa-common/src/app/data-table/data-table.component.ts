import { Component, Input, ViewChild, AfterContentInit, ChangeDetectorRef, ContentChildren, EventEmitter, Output } from '@angular/core';
import { MatTableDataSource, MatColumnDef, MatTable, MatCheckboxChange } from '@angular/material';
import { IRowClickEvent } from './models/row-click-event';


@Component({
  selector: 'app-data-table',
  templateUrl: './data-table.component.html'
})
export class DataTableComponent<DataType> implements AfterContentInit {
  @ViewChild('table', { static: false }) public matTable: MatTable<DataType>;
  @ContentChildren(MatColumnDef) public columns: MatColumnDef[];

  // Inputs
  @Input() public dataSource: MatTableDataSource<DataType>;
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
  public setRowSelected(index: number, selected: boolean) {
    this.rowSelection[index] = selected;
    this.updateRowSelectedState();
    console.log(this.rowSelection);
  }

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

  // MISC. UTIL FUNCTIONS
  public getSelectedRowsData(): DataType[] {
    return this.rowSelection.map((_row, index) => this.dataSource.data[index]);
  }

}
