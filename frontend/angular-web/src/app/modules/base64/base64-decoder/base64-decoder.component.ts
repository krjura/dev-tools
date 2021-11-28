import {Component, OnInit} from '@angular/core';
import {HttpClient, HttpHeaders} from '@angular/common/http';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

import {GlobalAlertService} from '@shared-services/global-alert.service'

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
        [
          Validators.required,
          Validators.minLength(1)
        ]
      ],
      outputFormat: [
        'application/octet-stream',
        [Validators.required]
      ],
      outputFilename: [
        'base64-decode-value.bin',
        [
          Validators.required,
          Validators.pattern('[a-zA-Z0-9\.-]+'),
          Validators.minLength(5),
          Validators.maxLength(30)]
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
        this.form.controls['data'].value,
        {headers: headers, responseType: 'arraybuffer', withCredentials: true})
      .subscribe(result => {

        const file = new File([result], this.determineOutputFilename(), {type: this.determineOutputFormat()});
        this.download(file);

        this.form.controls['data'].patchValue('');
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  private determineOutputFilename() {
    if (this.form.controls['outputFilename'].valid) {
      return this.form.controls['outputFilename'].value;
    } else {
      return 'base64-decode.bin';
    }
  }

  private determineOutputFormat() {
    if (this.form.controls['outputFormat'].valid) {
      return this.form.controls['outputFormat'].value;
    } else {
      return 'application/octet-stream';
    }
  }

  download(data: any) {
    const url = window.URL.createObjectURL(data);

    window.open(url, '_blank');
  }
}
