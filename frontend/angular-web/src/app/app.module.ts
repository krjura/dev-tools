import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";

import {ClipboardModule} from "ngx-clipboard";
import {ToastrModule} from "ngx-toastr";
import {TranslateModule} from "@ngx-translate/core";

import {SharedModule} from "./modules/shared/shared.module";

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    AppRoutingModule,

    // third-party
    ClipboardModule,
    ToastrModule.forRoot(),
    TranslateModule.forRoot(),

    // my own
    SharedModule,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
