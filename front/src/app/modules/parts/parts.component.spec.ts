import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PartsComponent } from './parts.component';

describe('PartsComponent', () => {
  let component: PartsComponent;
  let fixture: ComponentFixture<PartsComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [PartsComponent]
    });
    fixture = TestBed.createComponent(PartsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
