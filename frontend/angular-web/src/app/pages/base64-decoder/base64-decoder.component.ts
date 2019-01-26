import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';

import { GlobalAlertService } from '../../shared/services/global-alert.service';

@Component({
  selector: 'app-base64-decoder',
  templateUrl: './base64-decoder.component.html',
  styleUrls: ['./base64-decoder.component.css']
})
export class Base64DecoderComponent implements OnInit {

  form: FormGroup;

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

  decodeData() {
    const headers = new HttpHeaders()
      .append('Content-Type', 'text/plain')
      .append('Accept', 'application/octet-stream');

    this
      .http
      .post(
        '/api/v1/base64/decode',
        this.form.controls.data.value,
        {headers: headers, responseType: 'arraybuffer', withCredentials: true})
      .subscribe(result => {

        const file = new File([result], 'base64-decode-value.bin', {type: 'application/octet-stream'});
        this.download(file);

        this.form.controls.data.patchValue('');
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  download(data: any) {
    const url = window.URL.createObjectURL(data);

    window.open(url, '_blank');
  }
}
