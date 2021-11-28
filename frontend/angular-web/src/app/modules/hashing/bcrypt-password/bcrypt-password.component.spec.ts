import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { BCryptPasswordComponent } from './bcrypt-password.component';

describe('BCryptPasswordComponent', () => {
  let component: BCryptPasswordComponent;
  let fixture: ComponentFixture<BCryptPasswordComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ BCryptPasswordComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(BCryptPasswordComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
