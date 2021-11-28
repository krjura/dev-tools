import {NgModule} from '@angular/core';
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import {TranslateModule} from "@ngx-translate/core";

import {SharedModule} from "../shared/shared.module";

import {Base64EncoderComponent} from './base64-encoder/base64-encoder.component';
import {Base64DecoderComponent} from './base64-decoder/base64-decoder.component';

const routes: Routes = [
  {
    path: 'base64-encoder',
    component: Base64EncoderComponent
  },
  {
    path: 'base64-decoder',
    component: Base64DecoderComponent
  },
];

@NgModule({
  declarations: [
    Base64EncoderComponent,
    Base64DecoderComponent
  ],
  imports: [
    // angular
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    RouterModule.forChild(routes),

    // 3rd party
    TranslateModule.forChild(),

    // my own
    SharedModule,
  ],
})
export class Base64Module { }
