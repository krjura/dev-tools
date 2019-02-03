import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';

import { GlobalAlertService } from '../../shared/services/global-alert.service';
import { StorageService } from '../../shared/services/storage.service';

const ITERATIONS_LOCAL_STORAGE_KEY = 'BCryptPasswordComponent.iterations';

@Component({
  selector: 'app-bcrypt-password',
  templateUrl: './bcrypt-password.component.html',
  styleUrls: ['./bcrypt-password.component.css']
})
export class BCryptPasswordComponent implements OnInit {

  form: FormGroup;

  result: BCryptWebResponseModel = null;

  isContentCopied = false;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private alertService: GlobalAlertService,
    private storageService: StorageService) {

  }

  ngOnInit() {

    const defaultIterations = parseInt(this.storageService.load(ITERATIONS_LOCAL_STORAGE_KEY, '4'), 10);

    this.form = this.fb.group({
      iterations: [defaultIterations],
      data: [
        '',
        [Validators.required, Validators.maxLength(100), Validators.minLength(1)]
      ]
    });
  }

  execute() {
    if (this.form.controls.data.value === null || this.form.controls.data.value.length === 0) {
      return;
    }

    this.storageService.save(ITERATIONS_LOCAL_STORAGE_KEY, this.form.controls.iterations.value);

    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json;charset=UTF-8')
      .append('Accept', 'application/json;charset=UTF-8');

    const request = {
      iterations: this.form.controls.iterations.value,
      data: this.form.controls.data.value
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

  clearResult() {
    this.result = null;
  }
}
