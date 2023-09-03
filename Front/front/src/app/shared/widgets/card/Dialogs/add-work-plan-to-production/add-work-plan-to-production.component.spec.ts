import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddWorkPlanToProductionComponent } from './add-work-plan-to-production.component';

describe('AddWorkPlanToProductionComponent', () => {
  let component: AddWorkPlanToProductionComponent;
  let fixture: ComponentFixture<AddWorkPlanToProductionComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [AddWorkPlanToProductionComponent]
    });
    fixture = TestBed.createComponent(AddWorkPlanToProductionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
