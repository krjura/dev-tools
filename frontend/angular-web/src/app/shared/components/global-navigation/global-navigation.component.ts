import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-global-navigation',
  templateUrl: './global-navigation.component.html',
  styleUrls: ['./global-navigation.component.css']
})
export class GlobalNavigationComponent implements OnInit {

  showSidebar = false;

  collapse = true;
  collapseIn = false;

  constructor() {

  }

  ngOnInit() {
  }

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
