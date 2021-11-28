import {Component} from '@angular/core';
import {TranslateService} from '@ngx-translate/core';
import {translateEn} from './app.translate';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'angular-web';

  constructor(translate: TranslateService) {

    translate.setDefaultLang('en');
    translate.use('en');
    translate.setTranslation('en', translateEn);
  }
}
