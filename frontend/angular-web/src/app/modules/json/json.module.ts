import {NgModule} from '@angular/core';
import {CommonModule} from "@angular/common";
import {RouterModule, Routes} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import {TranslateModule} from "@ngx-translate/core";

import {SharedModule} from "../shared/shared.module";

import {JsonpfComponent} from "./jsonpf/jsonpf.component";

const routes: Routes = [
  {
    path: 'jsonpf',
    component: JsonpfComponent
  },
];

@NgModule({
  declarations: [
    JsonpfComponent,
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
export class JsonModule { }
