import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EditWorkplanDialogComponent } from './edit-workplan-dialog.component';

describe('EditWorkplanDialogComponent', () => {
  let component: EditWorkplanDialogComponent;
  let fixture: ComponentFixture<EditWorkplanDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [EditWorkplanDialogComponent]
    });
    fixture = TestBed.createComponent(EditWorkplanDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
