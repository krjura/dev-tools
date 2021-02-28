import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { HroibComponent } from './hroib.component';

describe('HroibComponent', () => {
  let component: HroibComponent;
  let fixture: ComponentFixture<HroibComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ HroibComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(HroibComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
