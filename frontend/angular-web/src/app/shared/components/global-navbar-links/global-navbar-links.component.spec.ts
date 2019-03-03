import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GlobalNavbarLinksComponent } from './global-navbar-links.component';

describe('GlobalNavbarLinksComponent', () => {
  let component: GlobalNavbarLinksComponent;
  let fixture: ComponentFixture<GlobalNavbarLinksComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GlobalNavbarLinksComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlobalNavbarLinksComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
