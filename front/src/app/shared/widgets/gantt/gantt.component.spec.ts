import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GanttComponent } from './gantt.component';

describe('GanttComponent', () => {
  let component: GanttComponent;
  let fixture: ComponentFixture<GanttComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GanttComponent]
    });
    fixture = TestBed.createComponent(GanttComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
