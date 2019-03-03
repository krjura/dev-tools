import { Injectable, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { UserInfoResponseModel } from '../model/user-info-response.model';

import { GlobalAlertService } from './global-alert.service';
import { GlobalHubService } from './global-hub.service';
import {UserInfoEventModel} from '../events/user-info-event.model';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService {

  authenticated = false;
  userInfo: UserInfoResponseModel = null;

  constructor(
    private http: HttpClient,
    private alertService: GlobalAlertService,
    private hub: GlobalHubService) {

  }

  loadUserInfo() {
    const headers = new HttpHeaders()
      .append('Accept', 'application/json;charset=UTF-8');

    this
      .http
      .get<UserInfoResponseModel>(
        '/api/v1/auth/user-info',
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {
        this.authenticated = response.authenticated;
        this.userInfo = response;

        this.hub.pushToAuthNotifications(new UserInfoEventModel(response.authenticated, response.username));
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }
}
