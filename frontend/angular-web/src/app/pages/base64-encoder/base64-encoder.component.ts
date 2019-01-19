import { Component, OnInit } from '@angular/core';
import {HttpClient, HttpHeaders, HttpResponse} from '@angular/common/http';
import {Observable} from "rxjs";

@Component({
  selector: 'app-base64-encoder',
  templateUrl: './base64-encoder.component.html',
  styleUrls: ['./base64-encoder.component.css']
})
export class Base64EncoderComponent implements OnInit {

  model = {
    data: ""
  };

  results = [];

  constructor(private http: HttpClient) {

  }

  ngOnInit() {

  }

  encodeData() {
    const headers = new HttpHeaders()
      .append('Content-Type', 'application/octet-stream')
      .append('Accept', 'text/plain');

    /*
    this
      .http
      .post<HttpResponse<string>("/api/v1/base64/encode", this.model.data, options)
      .subscribe(result => {
        this.results.push({ data: this.model.data, value: result});
      });
      */

    this
      .http
      .post(
        "/api/v1/base64/encode",
        this.model.data,
        {headers: headers, responseType: 'text', withCredentials: true})
      .subscribe(result => {
        this.results.push({ data: this.model.data, value: result});
      });
  }
}
