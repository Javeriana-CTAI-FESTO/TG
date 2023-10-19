import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddPartComponent } from './add-part.component';

describe('AddPartComponent', () => {
  let component: AddPartComponent;
  let fixture: ComponentFixture<AddPartComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddPartComponent]
    });
    fixture = TestBed.createComponent(AddPartComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
