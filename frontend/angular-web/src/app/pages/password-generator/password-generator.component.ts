import {Component, OnInit} from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ClipboardService } from 'ngx-clipboard';

import { GlobalAlertService } from '../../shared/services/global-alert.service';
import { PasswordRequestModel} from './model/password-request.model';
import { PasswordResponseModel } from './model/password-response.model';
import { GeneratedPasswordModel } from './model/generated-password.model';
import { StorageService } from '../../shared/services/storage.service';

const CHARACTER_COUNT_LOCAL_STORAGE_KEY = 'PasswordGeneratorComponent.characterCount';

@Component({
  selector: 'app-password-generator',
  templateUrl: './password-generator.component.html',
  styleUrls: ['./password-generator.component.css']
})
export class PasswordGeneratorComponent  implements OnInit {

  form: FormGroup;
  optionValues: string[];

  generatedPasswords: GeneratedPasswordModel[] = [];

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private clipboardService: ClipboardService,
    private alertService: GlobalAlertService,
    private storageService: StorageService) {
  }

  private generatePasswordLengthOptions() {
    this.optionValues = new Array(35);
    for (let counter = 0; counter <= 35; counter++) {
      this.optionValues[counter] = (counter + 5).toString();
    }
  }

  ngOnInit(): void {
    this.generatePasswordLengthOptions();

    const defaultCharacterCount = parseInt(this.storageService.load(CHARACTER_COUNT_LOCAL_STORAGE_KEY, '15'), 10);

    this.form = this.fb.group({
      useCapitalLetters: [true],
      useSmallLetters: [true],
      useNumbers: [true],
      characterCount: [defaultCharacterCount, [Validators.required]]
    });
  }

  execute() {

    this.storageService.save(CHARACTER_COUNT_LOCAL_STORAGE_KEY, this.form.controls.characterCount.value);

    const headers = new HttpHeaders()
      .append('Content-Type', 'application/json;charset=UTF-8')
      .append('Accept', 'application/json;charset=UTF-8');

    const request: PasswordRequestModel = {
      useCapitalLetters: this.form.controls.useCapitalLetters.value,
      useSmallLetters: this.form.controls.useSmallLetters.value,
      useNumbers: this.form.controls.useNumbers.value,
      characterCount: this.form.controls.characterCount.value,
    };

    this
      .http
      .post<PasswordResponseModel>(
        '/api/v1/password/generate',
        request,
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {
        this.generatedPasswords.push({isContentCopied: false, password: response.password});
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  copyPasswordToClipboard(index: number) {
    const password: GeneratedPasswordModel = this.generatedPasswords[index];

    password.isContentCopied = this.clipboardService.copyFromContent(password.password);
    this.clearContentCopied(index);
  }

  clearContentCopied(index: number) {
    const that = this;

    setTimeout(function () {
      that.generatedPasswords[index].isContentCopied = false;
    }, 2000);
  }

  clearPasswords() {
    this.generatedPasswords = [];
  }
}
