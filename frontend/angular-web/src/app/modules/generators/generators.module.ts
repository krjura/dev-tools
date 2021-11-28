import { NgModule } from '@angular/core';
import { CommonModule } from "@angular/common";
import { RouterModule, Routes } from "@angular/router";
import { FormsModule, ReactiveFormsModule } from "@angular/forms";
import { HttpClientModule } from "@angular/common/http";

import { TranslateModule } from "@ngx-translate/core";
;
import { SharedModule } from "../shared/shared.module"

import { PasswordGeneratorComponent } from "./password-generator/password-generator.component";
import { UuidGeneratorComponent } from "./uuid-generator/uuid-generator.component";
import { PkiKeyGeneratorComponent } from "./pki-key-generator/pki-key-generator.component";

const routes: Routes = [
  {
    path: 'password-generator',
    component: PasswordGeneratorComponent
  },
  {
    path: 'uuid-generate',
    component: UuidGeneratorComponent
  },
  {
    path: 'pki-key-generator',
    component: PkiKeyGeneratorComponent
  },
];

@NgModule({
  declarations: [
    PasswordGeneratorComponent,
    UuidGeneratorComponent,
    PkiKeyGeneratorComponent
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
export class GeneratorsModule { }
