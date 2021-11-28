import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { GlobalNavigationComponent } from './global-navigation.component';

describe('GlobalNavigationComponent', () => {
  let component: GlobalNavigationComponent;
  let fixture: ComponentFixture<GlobalNavigationComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ GlobalNavigationComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GlobalNavigationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
