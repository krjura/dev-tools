import { Component, Inject, OnDestroy, OnInit } from '@angular/core';
import { DOCUMENT } from '@angular/common';
import { GlobalHubService } from '../../services/global-hub.service';
import { PartialObserver, Subscription } from 'rxjs';
import { EventModel } from '../../events/event.model';
import { UserInfoEventModel } from '../../events/user-info-event.model';

@Component({
  selector: 'app-global-navbar-links',
  templateUrl: './global-navbar-links.component.html',
  styleUrls: ['./global-navbar-links.component.css']
})
export class GlobalNavbarLinksComponent implements OnInit, OnDestroy {

  showDropdown = false;

  authenticated = false;

  authNotificationSubscription: Subscription = null;

  constructor(
    @Inject(DOCUMENT) private document: any,
    private hub: GlobalHubService) {

  }

  ngOnInit() {

    const authObserver: PartialObserver<EventModel> = {
      next: value => {
        if (value.type === 'UserInfoEventModel') {
          const userInfo: UserInfoEventModel = <UserInfoEventModel> value;

          this.authenticated = userInfo.authenticated;
          console.log('user authentication status is: ' + this.authenticated);
        }
      },
      error: err => {
        console.error('Observer got an error: ' + err);
      },
      complete: () => {
        console.log('Observer got a complete notification');
      },
    };

    this.authNotificationSubscription = this
      .hub
      .registerToAuthNotifications(authObserver);
  }

  ngOnDestroy(): void {
    if (this.authNotificationSubscription != null) {
      this.authNotificationSubscription.unsubscribe();
    }
  }

  onDropdownToggle() {
    this.showDropdown = ! this.showDropdown;
  }

  onLogin() {
    this.document.location.href = '/oauth2/authorization/keycloak';
  }

  onLogout() {
    this.document.location.href = '/logout';
  }
}
