import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { Base64DecoderComponent } from './base64-decoder.component';

describe('Base64EncoderComponent', () => {
  let component: Base64DecoderComponent;
  let fixture: ComponentFixture<Base64DecoderComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ Base64DecoderComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(Base64DecoderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
