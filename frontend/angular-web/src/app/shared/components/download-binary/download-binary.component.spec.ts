import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DownloadBinaryComponent } from './download-binary.component';

describe('DownloadBinaryComponent', () => {
  let component: DownloadBinaryComponent;
  let fixture: ComponentFixture<DownloadBinaryComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DownloadBinaryComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DownloadBinaryComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
