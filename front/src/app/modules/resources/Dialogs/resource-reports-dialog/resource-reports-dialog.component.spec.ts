import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ResourceReportsDialogComponent } from './resource-reports-dialog.component';

describe('ResourceReportsDialogComponent', () => {
  let component: ResourceReportsDialogComponent;
  let fixture: ComponentFixture<ResourceReportsDialogComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [ResourceReportsDialogComponent]
    });
    fixture = TestBed.createComponent(ResourceReportsDialogComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
