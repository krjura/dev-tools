import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PkiKeyGeneratorComponent } from './pki-key-generator.component';

describe('PkiKeyGeneratorComponent', () => {
  let component: PkiKeyGeneratorComponent;
  let fixture: ComponentFixture<PkiKeyGeneratorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PkiKeyGeneratorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PkiKeyGeneratorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
