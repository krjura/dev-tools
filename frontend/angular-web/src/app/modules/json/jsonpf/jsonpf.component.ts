import {Component, OnInit} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';

@Component({
  selector: 'app-jsonpf',
  templateUrl: './jsonpf.component.html',
  styleUrls: ['./jsonpf.component.css']
})
export class JsonpfComponent implements OnInit {

  form: FormGroup;

  formattedJson: string = null;
  isError: boolean = false;

  constructor(
    private http: HttpClient,
    private fb: FormBuilder) {

  }

  ngOnInit() {
    this.form = this.fb.group({
      data: [
        '',
        [
          Validators.required,
          Validators.minLength(1)
        ]
      ]
    });
  }

  format() {
    try {

      let data = this.form.controls['data'].value;
      let obj = JSON.parse(data);

      this.formattedJson = JSON.stringify(obj, null, 2);
      this.isError = false;
    } catch (error: unknown) {

      if (error instanceof Error) {
        this.formattedJson = error.message;
      } else {
        this.formattedJson = "unknown error";
      }

      this.isError = true;
    }
  }
}
