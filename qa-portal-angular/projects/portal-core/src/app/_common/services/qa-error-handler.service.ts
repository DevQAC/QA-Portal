import {Injectable} from '@angular/core';
import {Router} from '@angular/router';
import {HttpErrorResponse} from '@angular/common/http';
import * as HttpStatus from 'http-status-codes';
import {Observable, of, throwError} from 'rxjs';
import {QaToastrService} from './qa-toastr.service';

@Injectable()
export class QaErrorHandlerService {

  constructor(private router: Router,
              private toastrService: QaToastrService) {
  }

  public handleError(error: HttpErrorResponse): void {
    this.processError(error).subscribe(
      (done) => {
        // Nothing to do as error is severe and we've redirected to the severe error page
      },
      (msg) => {
        this.toastrService.showError(msg);
      }
    );
  }

  private processError(error: HttpErrorResponse): Observable<string> {
    if (error.error instanceof ErrorEvent) {
      // A client-side or network error occurred. Display in an error toast.
      return throwError(error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      if (error.status === HttpStatus.INTERNAL_SERVER_ERROR) {
        // Navigate to severe error page and pass the error message to be displayed
        this.router.navigate(['qa/portal/error', {errorMsg: error.error}]);
        return of('');
      } else {
        // Display in an error toast.
        return throwError(error.error);
      }
    }
  }

}
