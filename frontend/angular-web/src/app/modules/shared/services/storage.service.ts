import {Injectable} from '@angular/core';

@Injectable({
  providedIn: 'root',
})
export class StorageService {

  localStorageAvailable = false;

  constructor() {
    this.localStorageAvailable = StorageService.isLocalStorageAvailable();
  }

  static isLocalStorageAvailable(): boolean {
    const test = 'test';

    try {
      window.localStorage.setItem(test, test);
      window.localStorage.removeItem(test);
      console.log('local storage is available');

      return true;
    } catch (e) {
      console.log('local storage is not available');
      return false;
    }
  }

  save(key: string, value: string) {
    if (this.localStorageAvailable) {
      window.localStorage.setItem(key, value);
    }
  }

  load(key: string, defaultValue: string): string {
    if (this.localStorageAvailable) {
      const value = window.localStorage.getItem(key);

      return value == null ? defaultValue : value;
    }

    return defaultValue;
  }
}
