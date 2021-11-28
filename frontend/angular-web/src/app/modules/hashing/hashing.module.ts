import {NgModule} from '@angular/core';
import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";

import {TranslateModule} from "@ngx-translate/core";

import {SharedModule} from "../shared/shared.module"

import {BCryptPasswordComponent} from "./bcrypt-password/bcrypt-password.component";

const routes: Routes = [
  {
    path: 'bcrypt-password',
    component: BCryptPasswordComponent
  },
];

@NgModule({
  declarations: [
    BCryptPasswordComponent,
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
export class HashingModule { }
