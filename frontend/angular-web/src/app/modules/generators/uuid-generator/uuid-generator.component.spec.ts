import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { UuidGeneratorComponent } from './uuid-generator.component';

describe('UuidGeneratorComponent', () => {
  let component: UuidGeneratorComponent;
  let fixture: ComponentFixture<UuidGeneratorComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ UuidGeneratorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(UuidGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
