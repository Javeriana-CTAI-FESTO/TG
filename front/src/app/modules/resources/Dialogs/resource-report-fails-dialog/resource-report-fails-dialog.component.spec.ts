import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceReportFailsDialogComponent } from './resource-report-fails-dialog.component';

describe('ResourceReportFailsDialogComponent', () => {
  let component: ResourceReportFailsDialogComponent;
  let fixture: ComponentFixture<ResourceReportFailsDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResourceReportFailsDialogComponent]
    });
    fixture = TestBed.createComponent(ResourceReportFailsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
