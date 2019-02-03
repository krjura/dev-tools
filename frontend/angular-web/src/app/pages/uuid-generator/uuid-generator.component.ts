import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UuidWebResponseModel } from './model/uuid-web-response.model';
import { GlobalAlertService } from '../../shared/services/global-alert.service';

@Component({
  selector: 'app-uuid-generator',
  templateUrl: './uuid-generator.component.html',
  styleUrls: ['./uuid-generator.component.css']
})
export class UuidGeneratorComponent implements OnInit {

  generatedUuids: UuidWebResponseModel[] = [];

  constructor(
    private http: HttpClient,
    private alertService: GlobalAlertService) {

  }

  ngOnInit() {

  }

  execute() {
    const headers = new HttpHeaders()
      .append('Accept', 'application/json;charset=UTF-8');

    this
      .http
      .get<UuidWebResponseModel>(
        '/api/v1/password/generate',
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {
        this.generatedUuids.push(response);
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  removeResults() {
    this.generatedUuids = [];
  }
}
