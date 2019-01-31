import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ClipboardService } from 'ngx-clipboard';

import { UuidWebResponseModel } from './model/uuid-web-response.model';
import { GeneratedUuidModel } from './model/generated-uuid.model';
import { GlobalAlertService } from '../../shared/services/global-alert.service';

@Component({
  selector: 'app-uuid-generator',
  templateUrl: './uuid-generator.component.html',
  styleUrls: ['./uuid-generator.component.css']
})
export class UuidGeneratorComponent implements OnInit {

  generatedUuids: GeneratedUuidModel[] = [];

  constructor(
    private http: HttpClient,
    private clipboardService: ClipboardService,
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
        this.generatedUuids.push({ uuid: response.uuid, isContentCopied: false });
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  copyPasswordToClipboard(index: number) {
    const result = this.generatedUuids[index];

    result.isContentCopied = this.clipboardService.copyFromContent(result.uuid);
    this.clearContentCopied(index);
  }

  clearContentCopied(index: number) {
    const that = this;

    setTimeout(function () {
      that.generatedUuids[index].isContentCopied = false;
    }, 2000);
  }
}
