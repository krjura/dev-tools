import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { Base64EncoderComponent } from './pages/base64-encoder/base64-encoder.component';
import { Base64DecoderComponent } from './pages/base64-decoder/base64-decoder.component';

@NgModule({
  declarations: [
    AppComponent,
    Base64EncoderComponent,
    Base64DecoderComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
