import { Injectable } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';

@Injectable({
  providedIn: 'root',
})
export class GlobalAlertService {

  constructor(
    private toastrService: ToastrService,
    protected translate: TranslateService ) {

  }

  errorResponseAlert(
    errorResponse: ErrorResponseModel) {

    let message = '';
    errorResponse.details.forEach( (errorDetail) => {
      message += this.translate.instant(errorDetail.reason);
    });

    const title = this.translate.instant('alerts.serverError');

    this.toastrService.success(message, title);
  }
}
