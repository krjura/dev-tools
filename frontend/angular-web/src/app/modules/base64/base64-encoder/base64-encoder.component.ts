import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {GlobalAlertService} from '@shared-services/global-alert.service';

import {Base64EncodeResultModel} from './base64-encode-result.model';

@Component({
  selector: 'app-base64-encoder',
  templateUrl: './base64-encoder.component.html',
  styleUrls: ['./base64-encoder.component.css']
})
export class Base64EncoderComponent implements OnInit {

  form: FormGroup;

  results: Base64EncodeResultModel[] = [];
  isResultCopied = false;
  isDataCopied = false;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private alertService: GlobalAlertService) {

  }

  ngOnInit() {
    this.form = this.fb.group({
      data: [
        '',
        [Validators.required, Validators.minLength(1)]
      ]
    });
  }

  encodeData() {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/octet-stream')
      .append('Accept', 'text/plain');

    this
      .http
      .post(
        '/api/v1/base64/encode',
        this.form.controls['data'].value,
        {headers: headers, responseType: 'text', withCredentials: true})
      .subscribe(result => {
        this.results.unshift({data: this.form.controls['data'].value, value: result});
        this.form.controls['data'].patchValue('');
      }, httpErrorResponse => {

      this.alertService.errorResponseAlert(httpErrorResponse.error);
    });
  }

  removeResult(index: number) {
    this.results.splice(index, 1);
  }
}
