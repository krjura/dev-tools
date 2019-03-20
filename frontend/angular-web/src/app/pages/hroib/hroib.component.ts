import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

import { GlobalAlertService } from '../../shared/services/global-alert.service';

import { OibValidationState } from './model/oib-validation-state.model';
import { HroibGenerateResponseModel } from './model/hroib-generate-response.model';
import { HroibValidateOibResponseModel } from './model/hroib-validate-oib-response.model';

@Component({
  selector: 'app-hroib',
  templateUrl: './hroib.component.html',
  styleUrls: ['./hroib.component.css']
})
export class HroibComponent implements OnInit {

  generatedOibs: HroibGenerateResponseModel[] = [];
  oibValidatorForm: FormGroup;

  oibIsValid: OibValidationState = OibValidationState.none;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private alertService: GlobalAlertService) {

  }

  ngOnInit() {
    this.oibValidatorForm = this.fb.group({
      oib: [
        '',
        [Validators.required, Validators.maxLength(11), Validators.minLength(11)]
      ]
    });
  }

  generateOib() {
    const headers = new HttpHeaders()
      .append('Accept', 'application/json;charset=UTF-8');

    this
      .http
      .get<HroibGenerateResponseModel>(
        '/api/v1/hroib/generate',
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {
        this.generatedOibs.push(response);
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  clearGeneratedOibs() {
    this.generatedOibs = [];
  }

  onOibValidatorInputChange(value: string) {
    console.log(value);

    if (! this.oibValidatorForm.controls.oib.valid) {
      this.oibIsValid = OibValidationState.nonValid;
      return;
    }

    const headers = new HttpHeaders()
      .append('Accept', 'application/json;charset=UTF-8');

    const url = '/api/v1/hroib/validate/' + this.oibValidatorForm.controls.oib.value;
    this
      .http
      .get<HroibValidateOibResponseModel>(
        url,
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {

        if (response.valid) {
          this.oibIsValid = OibValidationState.valid;
        } else {
          this.oibIsValid = OibValidationState.nonValid;
        }

      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }
}
