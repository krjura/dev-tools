import {NgModule} from '@angular/core';
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import {TranslateModule} from "@ngx-translate/core";

import {SharedModule} from "../shared/shared.module";

import {HroibComponent} from "./hroib/hroib.component";

const routes: Routes = [
  {
    path: 'hr-oib',
    component: HroibComponent
  }
];

@NgModule({
  declarations: [
    HroibComponent
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
export class CountryHrModule { }
