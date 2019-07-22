import {ToastrService} from 'ngx-toastr';
import {Injectable} from '@angular/core';

@Injectable()
export class QaToastrService {
  constructor(private toastr: ToastrService) {
  }

  showSuccess(msg: string) {
    this.toastr.success(msg, 'Success');
  }

  showError(msg: string) {
    this.toastr.error(msg, 'Error');

  }

  showInfo(msg: string) {
    this.toastr.info(msg, 'Information');
  }

  showWarning(msg: string) {
    this.toastr.warning(msg, 'Warning');
  }
}
