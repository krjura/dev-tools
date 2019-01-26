import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { ClipboardService } from 'ngx-clipboard';

import { Base64EncodeResultModel } from './base64-encode-result.model';
import { GlobalAlertService } from '../../shared/services/global-alert.service';

@Component({
  selector: 'app-base64-encoder',
  templateUrl: './base64-encoder.component.html',
  styleUrls: ['./base64-encoder.component.css']
})
export class Base64EncoderComponent implements OnInit {

  model = {
    data: ''
  };

  results: Base64EncodeResultModel[] = [];
  isResultCopied = false;
  isDataCopied = false;

  constructor(
    private http: HttpClient,
    private clipboardService: ClipboardService,
    private alertService: GlobalAlertService) {

  }

  ngOnInit() {

  }

  encodeData() {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/octet-stream')
      .append('Accept', 'text/plain');

    this
      .http
      .post(
        '/api/v1/base64/encode',
        this.model.data,
        {headers: headers, responseType: 'text', withCredentials: true})
      .subscribe(result => {
        this.results.unshift({data: this.model.data, value: result});
        this.model.data = '';
      }, httpErrorResponse => {

      this.alertService.errorResponseAlert(httpErrorResponse.error);
    });
  }

  removeResult(index: number) {
    this.results.splice(index, 1);
  }

  downloadValue(index: number) {
    this.download(this.results[index].value, 'base64-encode-value.txt');
  }

  downloadData(index: number) {
    this.download(this.results[index].data, 'base64-encode-data.txt');
  }

  download(data: string, filename: string) {
    const file = new File([data], filename, {type: 'application/octet-stream'});
    const url = window.URL.createObjectURL(file);

    window.open(url, '_blank');
    window.URL.revokeObjectURL(url);
  }

  copyResultToClipboard(index) {
    this.isResultCopied = this.clipboardService.copyFromContent(this.results[index].value);
    this.clearResultToClipboard();
  }

  clearResultToClipboard() {
    const that = this;

    setTimeout(function () {
      that.isResultCopied = false;
    }, 2000);
  }

  copyDataToClipboard(index: number) {
    this.isDataCopied = this.clipboardService.copyFromContent(this.results[index].data);
    this.clearDataToClipboard();
  }

  clearDataToClipboard() {
    const that = this;

    setTimeout(function () {
      that.isDataCopied = false;
    }, 2000);
  }
}
