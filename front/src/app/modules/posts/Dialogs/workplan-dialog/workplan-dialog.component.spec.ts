import { ComponentFixture, TestBed } from '@angular/core/testing';

import { WorkplanDialogComponent } from './workplan-dialog.component';

describe('WorkplanDialogComponent', () => {
  let component: WorkplanDialogComponent;
  let fixture: ComponentFixture<WorkplanDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [WorkplanDialogComponent]
    });
    fixture = TestBed.createComponent(WorkplanDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
