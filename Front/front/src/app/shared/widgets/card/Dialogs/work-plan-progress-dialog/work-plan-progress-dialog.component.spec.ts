import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkPlanProgressDialogComponent } from './work-plan-progress-dialog.component';

describe('WorkPlanProgressDialogComponent', () => {
  let component: WorkPlanProgressDialogComponent;
  let fixture: ComponentFixture<WorkPlanProgressDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorkPlanProgressDialogComponent]
    });
    fixture = TestBed.createComponent(WorkPlanProgressDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
