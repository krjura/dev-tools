import { ChangeDetectionStrategy, ChangeDetectorRef, Component, Input } from '@angular/core';

import { ClipboardService } from 'ngx-clipboard';

@Component({
  selector: 'app-copy-to-clipboard',
  templateUrl: './copy-to-clipboard.component.html',
  styleUrls: ['./copy-to-clipboard.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class CopyToClipboardComponent {

  @Input() data: string;

  isContentCopied: boolean;

  constructor(
    private clipboardService: ClipboardService,
    private cdr: ChangeDetectorRef) {

  }

  copyToClipboard() {
    if (this.data === null || this.data.length === 0) {
      return;
    }

    this.isContentCopied = this.clipboardService.copyFromContent(this.data);
    this.clearCopiedFlag();
  }

  clearCopiedFlag() {
    const that = this;
    setTimeout(function () {
      that.isContentCopied = false;
      that.cdr.detectChanges();
    }, 2000);
  }
}
