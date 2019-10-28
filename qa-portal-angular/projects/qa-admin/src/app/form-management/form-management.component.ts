import { Component, OnInit, ViewChild } from '@angular/core';
import { DataTableComponent } from 'projects/qa-common/src/app/data-table/data-table.component';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { FormService } from '../_common/services/form.service';
import { Router, ActivatedRoute } from '@angular/router';
import { IRowClickEvent } from 'projects/qa-common/src/app/data-table/models/row-click-event';
import { NewFormDialogComponent } from './new-form-dialog/new-form-dialog.component';
import { FormModel } from 'projects/portal-core/src/app/_common/models/form.model';
import { QaErrorHandlerService } from 'projects/portal-core/src/app/_common/services/qa-error-handler.service';

@Component({
  selector: 'app-form-management',
  templateUrl: './form-management.component.html'
})
export class FormManagementComponent implements OnInit {

  @ViewChild('dataTable', { static: false }) dataTable: DataTableComponent<FormModel>;

  // SEARCH
  public searchInput = '';

  // TABLE
  public formsTableDataSource = new MatTableDataSource<FormModel>();
  public displayedColumns = ['formName', 'questionCategories', 'questions'];
  public rowSelection = [];
  public isLoading = true;

  constructor(
    private router: Router,
    private activatedRoute: ActivatedRoute,
    private formService: FormService,
    private errorService: QaErrorHandlerService,
    private dialog: MatDialog
  ) { }

  ngOnInit() {
    this.searchInput = this.activatedRoute.snapshot.queryParams.search || this.searchInput;
    this.performSearch();
  }

  public performSearch() {
    if (this.dataTable) { // Check if dataTable is defined. This isn't ready on first search (not that it matters)
      this.dataTable.deselectAllRows();
    }
    this.isLoading = true;
    this.router.navigate([], { relativeTo: this.activatedRoute, queryParams: { search: this.searchInput } });

    this.formService.getAllForms().subscribe(results => {
      this.formsTableDataSource.data = results;
      this.isLoading = false;
    }, err => this.errorService.handleError(err));
  }

  public onAddFormButtonClicked(): void {
    this.dialog.open(NewFormDialogComponent, {}).afterClosed().subscribe(data => {
      if (data) {
        this.performSearch();
      }
    });
  }

  onRowClicked(event: IRowClickEvent<any>): void {
    this.router.navigate(['qa', 'portal', 'admin', 'manage', 'forms', event.data.id]);
  }

  getQuestionCount(cats: any[]): number {
    return cats ? cats.map(cat => cat.questions).reduce<number>((prev, curr) => prev + curr.length || 0, 0) : 0;
  }

}
