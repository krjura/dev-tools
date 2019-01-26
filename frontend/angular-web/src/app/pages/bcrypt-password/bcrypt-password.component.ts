import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ClipboardService } from 'ngx-clipboard';
import { GlobalAlertService } from '../../shared/services/global-alert.service';

@Component({
  selector: 'app-bcrypt-password',
  templateUrl: './bcrypt-password.component.html',
  styleUrls: ['./bcrypt-password.component.css']
})
export class BCryptPasswordComponent implements OnInit {

  model = {
    iterations : 10,
    data: ''
  };

  result: BCryptWebResponseModel = null;

  isContentCopied = false;

  constructor(
    private http: HttpClient,
    private clipboardService: ClipboardService,
    private alertService: GlobalAlertService) {

  }

  ngOnInit() {

  }

  execute() {
    if (this.model.data === null || this.model.data.length === 0) {
      return;
    }

    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json;charset=UTF-8')
      .append('Accept', 'application/json;charset=UTF-8');

    const request = {
      iterations: this.model.iterations,
      data: this.model.data
    };

    this
      .http
      .post<BCryptWebResponseModel>(
        '/api/v1/bcrypt/password',
        request,
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {
        this.result = response;
      }, httpErrorResponse => {

          this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  copyData() {
    if (this.model.data === null || this.model.data.length ===  0) {
      return;
    }

    this.isContentCopied = this.clipboardService.copyFromContent(this.result.encoded);
    this.clearContentCopied();
  }

  clearContentCopied() {
    const that = this;

    setTimeout(function () {
      that.isContentCopied = false;
    }, 2000);
  }

  clearResult() {
    this.result = null;
  }
}
