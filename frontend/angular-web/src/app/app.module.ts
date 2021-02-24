import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { APP_INITIALIZER, NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';

import { ClipboardModule } from 'ngx-clipboard';
import { ToastrModule } from 'ngx-toastr';
import { TranslateModule } from '@ngx-translate/core';
import { ReactiveFormsModule } from '@angular/forms';

import { AppComponent } from './app.component';
import { Base64EncoderComponent } from './pages/base64-encoder/base64-encoder.component';
import { Base64DecoderComponent } from './pages/base64-decoder/base64-decoder.component';
import { BCryptPasswordComponent } from './pages/bcrypt-password/bcrypt-password.component';
import { PasswordGeneratorComponent } from './pages/password-generator/password-generator.component';
import { UuidGeneratorComponent } from './pages/uuid-generator/uuid-generator.component';

import { GlobalAlertService } from './shared/services/global-alert.service';
import { StorageService } from './shared/services/storage.service';
import { GlobalNavigationComponent } from './shared/components/global-navigation/global-navigation.component';
import { PkiKeyGeneratorComponent } from './pages/pki-key-generator/pki-key-generator.component';
import { CopyToClipboardComponent } from './shared/components/copy-to-clipboard/copy-to-clipboard.component';
import { DownloadBinaryComponent } from './shared/components/download-binary/download-binary.component';
import { AuthenticationService } from './shared/services/authentication.service';
import { GlobalHubService } from './shared/services/global-hub.service';
import { HroibComponent } from './pages/hroib/hroib.component';

@NgModule({
  declarations: [
    AppComponent,
    Base64EncoderComponent,
    Base64DecoderComponent,
    BCryptPasswordComponent,
    PasswordGeneratorComponent,
    UuidGeneratorComponent,
    GlobalNavigationComponent,
    PkiKeyGeneratorComponent,
    CopyToClipboardComponent,
    DownloadBinaryComponent,
    HroibComponent
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
    TranslateModule.forRoot()
  ],
  providers: [
    GlobalAlertService,
    StorageService,
    AuthenticationService,
    GlobalHubService,
    {
      provide: APP_INITIALIZER,
      useFactory: (authenticationService: AuthenticationService) => () => authenticationService.loadUserInfo(),
      deps: [AuthenticationService],
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
