import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { ClipboardModule } from 'ngx-clipboard';

import { AppComponent } from './app.component';
import { Base64EncoderComponent } from './pages/base64-encoder/base64-encoder.component';
import { Base64DecoderComponent } from './pages/base64-decoder/base64-decoder.component';
import { BCryptPasswordComponent } from './pages/bcrypt-password/bcrypt-password.component';

@NgModule({
  declarations: [
    AppComponent,
    Base64EncoderComponent,
    Base64DecoderComponent,
    BCryptPasswordComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule,

// third-party
    ClipboardModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
