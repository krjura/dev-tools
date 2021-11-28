import {ChangeDetectionStrategy, Component, Input} from '@angular/core';

@Component({
  selector: 'app-download-binary',
  templateUrl: './download-binary.component.html',
  styleUrls: ['./download-binary.component.css'],
  changeDetection: ChangeDetectionStrategy.OnPush
})
export class DownloadBinaryComponent {

  @Input() mediaType: string;
  @Input() filename: string;
  @Input() dataAsString: string;

  download() {
    const file = new File([this.dataAsString], this.filename, {type: this.mediaType});
    const url = window.URL.createObjectURL(file);

    window.open(url, '_blank');
    window.URL.revokeObjectURL(url);
  }

}
