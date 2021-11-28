import {NgModule} from '@angular/core';
import {CommonModule} from "@angular/common";
import {TranslateModule} from "@ngx-translate/core";
import {RouterModule} from "@angular/router";

import {GlobalAlertService} from "./services/global-alert.service";
import {StorageService} from "./services/storage.service";

import {CopyToClipboardComponent} from "./components/copy-to-clipboard/copy-to-clipboard.component";
import {DownloadBinaryComponent} from "./components/download-binary/download-binary.component";
import {GlobalNavigationComponent} from "./components/global-navigation/global-navigation.component";

@NgModule({
  imports: [
    // angular
    CommonModule,
    RouterModule,

    // 3rd party
    TranslateModule.forChild()
  ],
  declarations: [
    CopyToClipboardComponent,
    DownloadBinaryComponent,
    GlobalNavigationComponent
  ],
  exports: [
    CopyToClipboardComponent,
    DownloadBinaryComponent,
    GlobalNavigationComponent
  ],
  providers: [
    GlobalAlertService,
    StorageService
  ]
})
export class SharedModule { }
