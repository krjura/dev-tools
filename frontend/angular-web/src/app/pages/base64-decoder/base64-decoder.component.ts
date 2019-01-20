import { Component, OnInit } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';

@Component({
  selector: 'app-base64-decoder',
  templateUrl: './base64-decoder.component.html',
  styleUrls: ['./base64-decoder.component.css']
})
export class Base64DecoderComponent implements OnInit {

  model = {
    data: ''
  };

  constructor(private http: HttpClient) {

  }

  ngOnInit() {

  }

  decodeData() {
    const headers = new HttpHeaders()
      .append('Content-Type', 'text/plain')
      .append('Accept', 'application/octet-stream');

    this
      .http
      .post(
        '/api/v1/base64/decode',
        this.model.data,
        {headers: headers, responseType: 'arraybuffer', withCredentials: true})
      .subscribe(result => {

        const file = new File([result], 'base64-decode-value.bin', {type: 'application/octet-stream'});
        this.download(file);

        this.model.data = '';
      });
  }

  download(data: any) {
    const url = window.URL.createObjectURL(data);

    window.open(url, '_blank');
  }
}
