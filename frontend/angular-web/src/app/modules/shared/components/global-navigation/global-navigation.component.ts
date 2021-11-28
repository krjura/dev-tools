import {Component} from '@angular/core';

@Component({
  selector: 'app-global-navigation',
  templateUrl: './global-navigation.component.html',
  styleUrls: ['./global-navigation.component.css']
})
export class GlobalNavigationComponent {

  showSidebar = false;

  collapse = true;
  collapseIn = false;

  toggleSidebar() {
    const that = this;

    // if true then collapse
    if (this.showSidebar) {
      this.collapse = true;
      this.collapseIn = false;
      this.showSidebar = false;
    } else {
      this.collapse = true;
      this.collapseIn = true;
      this.showSidebar = true;
    }
  }
}
