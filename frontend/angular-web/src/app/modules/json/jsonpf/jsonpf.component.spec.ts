import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { JsonpfComponent } from './jsonpf.component';

describe('HroibComponent', () => {
  let component: JsonpfComponent;
  let fixture: ComponentFixture<JsonpfComponent>;

  beforeEach(waitForAsync(() => {
    TestBed.configureTestingModule({
      declarations: [ JsonpfComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(JsonpfComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
