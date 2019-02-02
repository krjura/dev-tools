import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { HttpClient, HttpHeaders } from '@angular/common/http';

import { ClipboardService } from 'ngx-clipboard';
import { GlobalAlertService } from '../../shared/services/global-alert.service';
import { StorageService } from '../../shared/services/storage.service';

import { GeneratePairResponseModel } from './model/generate-pair-response.model';
import { GeneratePairRequestModel } from './model/generate-pair-request.model';
import { GeneratePairViewModel } from './model/generate-pair-view.model';

const PREFERRED_ALGORITHM_KEY = 'PkiKeyGeneratorComponent.algorithm';
const PREFERRED_KEY_SIZE_KEY = 'PkiKeyGeneratorComponent.keySize';

@Component({
  selector: 'app-pki-key-generator',
  templateUrl: './pki-key-generator.component.html',
  styleUrls: ['./pki-key-generator.component.css']
})
export class PkiKeyGeneratorComponent implements OnInit {

  form: FormGroup;
  keySizes = ['512', '1024', '2048', '4096'];
  algorithms = ['RSA'];

  generatedPairs: GeneratePairViewModel[] = [];

  constructor(
    private http: HttpClient,
    private fb: FormBuilder,
    private clipboardService: ClipboardService,
    private alertService: GlobalAlertService,
    private storageService: StorageService) {

  }

  ngOnInit() {
    const defaultAlgorithm = this.storageService.load(PREFERRED_ALGORITHM_KEY, 'RSA');
    const defaultKeySize = this.storageService.load(PREFERRED_KEY_SIZE_KEY, '2048');

    this.form = this.fb.group({
      keySize: [defaultKeySize, [Validators.required]],
      algorithm: [defaultAlgorithm, [Validators.required]],
    });
  }

  execute() {
    this.storageService.save(PREFERRED_ALGORITHM_KEY, this.form.controls.algorithm.value);
    this.storageService.save(PREFERRED_KEY_SIZE_KEY, this.form.controls.keySize.value);

    const headers = new HttpHeaders()
      .append('Accept', 'application/json;charset=UTF-8');

    const request: GeneratePairRequestModel = {
      keySize: 'L_' + this.form.controls.keySize.value,
      algorithm: this.form.controls.algorithm.value
    };

    this
      .http
      .post<GeneratePairResponseModel>(
        '/api/v1/pki/generate-pair',
        request,
        {headers: headers, responseType: 'json', withCredentials: true})
      .subscribe(response => {
        this.generatedPairs.push({response: response, isPrivateKeyCopied: false, isPublicKeyCopied: false});
      }, httpErrorResponse => {

        this.alertService.errorResponseAlert(httpErrorResponse.error);
      });
  }

  removeResult(index: number) {
    this.generatedPairs.splice(index, 1);
  }

  copyPublicKeyToClipboard(index: number) {
    const selection = this.generatedPairs[index];

    selection.isPublicKeyCopied = this.clipboardService.copyFromContent(selection.response.public);
    this.clearPublicKeyCopiedFlag(selection);
  }

  clearPublicKeyCopiedFlag(selection: GeneratePairViewModel) {
    setTimeout(function () {
      selection.isPublicKeyCopied = false;
    }, 2000);
  }

  copyPrivateKeyToClipboard(index: number) {
    const selection = this.generatedPairs[index];

    selection.isPrivateKeyCopied = this.clipboardService.copyFromContent(selection.response.private);
    this.clearPrivateKeyCopiedFlag(selection);
  }

  clearPrivateKeyCopiedFlag(selection: GeneratePairViewModel) {
    setTimeout(function () {
      selection.isPublicKeyCopied = false;
    }, 2000);
  }

  downloadPublicKey(index: number) {
    const selection = this.generatedPairs[index];

    this.download(selection.response.public, 'public-key.pem');
  }

  downloadPrivateKey(index: number) {
    const selection = this.generatedPairs[index];

    this.download(selection.response.private, 'private-key.pem');
  }

  download(data: string, filename: string) {
    const file = new File([data], filename, {type: 'application/x-pem-file'});
    const url = window.URL.createObjectURL(file);

    window.open(url, '_blank');
    window.URL.revokeObjectURL(url);
  }
}
