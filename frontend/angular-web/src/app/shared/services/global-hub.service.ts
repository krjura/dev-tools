import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { PartialObserver } from 'rxjs/src/internal/types';
import { UserInfoEventModel } from '../events/user-info-event.model';
import { EventModel } from '../events/event.model';

@Injectable({
  providedIn: 'root',
})
export class GlobalHubService {

  private authNotifications: BehaviorSubject<EventModel>;

  constructor() {
    this.authNotifications = new BehaviorSubject<EventModel>(new UserInfoEventModel(false, null));
  }

  registerToAuthNotifications(next: PartialObserver<EventModel>) {
    return this
      .authNotifications
      .subscribe(next);
  }

  pushToAuthNotifications(next: EventModel) {
    this.authNotifications.next(next);
  }
}
