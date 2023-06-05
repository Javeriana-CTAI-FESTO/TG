import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DefaultWorkPlanComponent } from './default-work-plan.component';

describe('DefaultWorkPlanComponent', () => {
  let component: DefaultWorkPlanComponent;
  let fixture: ComponentFixture<DefaultWorkPlanComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [DefaultWorkPlanComponent]
    });
    fixture = TestBed.createComponent(DefaultWorkPlanComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
