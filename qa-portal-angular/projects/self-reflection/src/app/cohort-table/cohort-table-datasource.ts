import { DataSource } from '@angular/cdk/collections';
import { MatPaginator, MatSort } from '@angular/material';
import { map } from 'rxjs/operators';
import { Observable, of as observableOf, merge } from 'rxjs';

// TODO: Replace this with your own data model type
export interface CohortTableItem {
  firstName: string;
  secondName: string;
  week1: number;
  week2: number;
}

// TODO: replace this with real data from your application
const EXAMPLE_DATA: CohortTableItem[] = [
  { firstName: 'Carl', secondName: 'Malone', week1: 7.5, week2: 6.9 },
  { firstName: 'John', secondName: 'Stockton', week1: 7.2, week2: 6.4 },
  { firstName: 'Luke', secondName: 'Shaw', week1: 9.4, week2: 9.6 },
  { firstName: 'Mary', secondName: 'Magdalene', week1: 8.5, week2: 7.9 },
  { firstName: 'Lucy', secondName: 'Holmes', week1: 6.7, week2: 4.8 },

];

/**
 * Data source for the CohortTable view. This class should
 * encapsulate all logic for fetching and manipulating the displayed data
 * (including sorting, pagination, and filtering).
 */
export class CohortTableDataSource extends DataSource<CohortTableItem> {
  data: CohortTableItem[] = EXAMPLE_DATA;
  paginator: MatPaginator;
  sort: MatSort;

  constructor() {
    super();
  }

  /**
   * Connect this data source to the table. The table will only update when
   * the returned stream emits new items.
   * @returns A stream of the items to be rendered.
   */
  connect(): Observable<CohortTableItem[]> {
    // Combine everything that affects the rendered data into one update
    // stream for the data-table to consume.
    const dataMutations = [
      observableOf(this.data),
      this.paginator.page,
      this.sort.sortChange
    ];

    return merge(...dataMutations).pipe(map(() => {
      return this.getPagedData(this.getSortedData([...this.data]));
    }));
  }

  /**
   *  Called when the table is being destroyed. Use this function, to clean up
   * any open connections or free any held resources that were set up during connect.
   */
  disconnect() {}

  /**
   * Paginate the data (client-side). If you're using server-side pagination,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getPagedData(data: CohortTableItem[]) {
    const startIndex = this.paginator.pageIndex * this.paginator.pageSize;
    return data.splice(startIndex, this.paginator.pageSize);
  }

  /**
   * Sort the data (client-side). If you're using server-side sorting,
   * this would be replaced by requesting the appropriate data from the server.
   */
  private getSortedData(data: CohortTableItem[]) {
    if (!this.sort.active || this.sort.direction === '') {
      return data;
    }

    return data.sort((a, b) => {
      const isAsc = this.sort.direction === 'asc';
      switch (this.sort.active) {
        case 'firstName': return compare(a.firstName, b.firstName, isAsc);
        case 'secondName': return compare(a.secondName, b.secondName, isAsc);
        case 'week1': return compare(+a.week1, +b.week1, isAsc);
        case 'week2': return compare(+a.week2, +b.week2, isAsc);
        default: return 0;
      }
    });
  }
}

/** Simple sort comparator for example ID/Name columns (for client-side sorting). */
function compare(a, b, isAsc) {
  return (a < b ? -1 : 1) * (isAsc ? 1 : -1);
}
