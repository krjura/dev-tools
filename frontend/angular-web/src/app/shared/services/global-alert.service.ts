import { Inject, Injector, Injectable } from '@angular/core';

import { ToastrService } from 'ngx-toastr';
import { TranslateService } from '@ngx-translate/core';
import { ErrorResponseModel } from '../errors/error-response.model'

@Injectable({
  providedIn: 'root',
})
export class GlobalAlertService {

  constructor(
    @Inject(Injector) private injector: Injector,
    protected translate: TranslateService ) {

  }

  private get toastrService(): ToastrService {
    return this.injector.get(ToastrService);
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
