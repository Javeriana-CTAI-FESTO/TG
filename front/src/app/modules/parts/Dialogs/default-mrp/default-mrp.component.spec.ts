import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultMRPComponent } from './default-mrp.component';

describe('DefaultMRPComponent', () => {
  let component: DefaultMRPComponent;
  let fixture: ComponentFixture<DefaultMRPComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DefaultMRPComponent]
    });
    fixture = TestBed.createComponent(DefaultMRPComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
