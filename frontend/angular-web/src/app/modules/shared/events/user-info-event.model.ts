import {EventModel} from './event.model';

export class UserInfoEventModel extends EventModel {

  private readonly _authenticated: boolean;
  private readonly _username: string;

  constructor(authenticated: boolean, username: string) {
    super('UserInfoEventModel');

    this._authenticated = authenticated;
    this._username = username;
  }

  get authenticated(): boolean {
    return this._authenticated;
  }

  get username(): string {
    return this._username;
  }
}
